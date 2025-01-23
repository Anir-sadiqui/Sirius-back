package Episante.back.Service;


import Episante.back.Models.Patient;
import Episante.back.Models.Sexe;
import Episante.back.Repository.IPatientrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private IPatientrepository patientDao;

    public List<Patient> getAllPatients() {return patientDao.findAll();}
    public Optional<Patient> getById(long id) {return patientDao.findById(id);}
    public List<Patient> getPatientByName(String name) {return patientDao.findByNom(name);}
    public List<Patient> getPatientByPrenom(String prenom) {return patientDao.findByPrenom(prenom);}

    public void deletePatient(Optional<Patient> patient) {
        Patient patientEntity = patient.orElseThrow(() ->
                new IllegalArgumentException("Utilisateur introuvable"));
        patientDao.delete(patientEntity);
    }
    public void Inscription(Patient patient) {
        if (patientDao.existsByEmail(patient.getEmail())) {
            throw new IllegalArgumentException("Cet email est déjà utilisé !");
        }
        if (patient.getMdp().length() < 6) {
            throw new IllegalArgumentException("Le mot de passe doit contenir au moins 6 caractères.");
        }
        patientDao.save(patient);
    }
    public Patient getByEmail(String email) {
        if (!patientDao.existsByEmail(email)) {
            throw new IllegalArgumentException("Cet email n'existe pas!");
        }
        return patientDao.findByEmail(email);
    }

    public Boolean Login(String email, String password) {
        if (!patientDao.existsByEmail(email)); {
            throw new IllegalArgumentException("Cet email n'existe pas! !");
        }
        if (password.equals(patientDao.findByEmail(email).getMdp())) {
            return true;
        }
        return false;
    }


    public String BilanS(Patient patient) {
        double poids = Double.parseDouble(patient.getPoids());
        double taille = Double.parseDouble(patient.getTaille());
        double imc = poids / (taille * taille);
        double seuilInsuffisance = 18.5;
        double seuilNormal = 24.9;

        if (patient.getSexe().equals(Sexe.FEMME)) {
            seuilInsuffisance = 18.0;
            seuilNormal = 24.5;
        }

        if (patient.getAge() > 65) {
            seuilNormal = 27.0;
        }

        String bilan;
        if (imc < seuilInsuffisance) {
            bilan = String.format(
                    "Bonjour %s %s, votre IMC est %.2f. Vous êtes en insuffisance pondérale. " +
                            "Nous vous recommandons de consulter un nutritionniste pour évaluer votre alimentation " +
                            "et d'adopter une alimentation plus riche en calories et en nutriments.",
                    patient.getPrenom(), patient.getNom(), imc
            );
        } else if (imc < seuilNormal) {
            bilan = String.format(
                    "Bonjour %s %s, votre IMC est %.2f. Vous avez un poids normal. " +
                            "Continuez à maintenir un mode de vie équilibré avec une alimentation variée et une activité physique régulière.",
                    patient.getPrenom(), patient.getNom(), imc
            );
        } else if (imc < 30) {
            bilan = String.format(
                    "Bonjour %s %s, votre IMC est %.2f. Vous êtes en surpoids. " +
                            "Nous vous conseillons d'augmenter votre activité physique et de surveiller votre alimentation " +
                            "en limitant les aliments riches en sucres et en graisses.",
                    patient.getPrenom(), patient.getNom(), imc
            );
        } else {
            bilan = String.format(
                    "Bonjour %s %s, votre IMC est %.2f. Vous êtes en situation d'obésité. " +
                            "Nous vous recommandons vivement de consulter un professionnel de santé pour un suivi adapté, " +
                            "et de privilégier une alimentation saine ainsi qu'une activité physique régulière adaptée.",
                    patient.getPrenom(), patient.getNom(), imc
            );
        }

        return bilan;
    }


    }




