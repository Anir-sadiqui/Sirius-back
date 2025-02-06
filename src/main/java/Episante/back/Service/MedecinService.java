package Episante.back.Service;

import Episante.back.Models.*;
import Episante.back.Repository.MedecinRepository;
import Episante.back.Repository.DisponibiliteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class MedecinService {
    private final MedecinRepository medecinRepository;
    private final DisponibiliteRepository disponibiliteRepository;

    public MedecinService(MedecinRepository medecinRepository, DisponibiliteRepository disponibiliteRepository) {
        this.medecinRepository = medecinRepository;
        this.disponibiliteRepository = disponibiliteRepository;
    }
    public void genererDisponibilitesAleatoires() {
        List<Medecin> medecins = medecinRepository.findAll();
        Random random = new Random();

        JourSemaine[] jours = JourSemaine.values(); // Lundi - Vendredi
        Periode[] periodes = Periode.values(); // Matin, Soir

        for (Medecin medecin : medecins) {
            int nbJours = random.nextInt(5) + 1; // Nombre de jours de disponibilité (1 à 5)

            for (int i = 0; i < nbJours; i++) {
                JourSemaine jour = jours[random.nextInt(jours.length)]; // Jour aléatoire entre Lundi et Vendredi
                Periode periode = periodes[random.nextInt(periodes.length)]; // Matin ou Soir

                // Vérifier si la disponibilité existe déjà
                if (disponibiliteRepository.findByMedecinAndJourAndPeriode(medecin, jour, periode).isEmpty()) {
                    Disponibilite disponibilite = new Disponibilite();
                    disponibilite.setJour(jour);
                    disponibilite.setPeriode(periode);
                    disponibilite.setMedecin(medecin);
                    disponibiliteRepository.save(disponibilite); // Sauvegarde uniquement si non existante
                }
            }
        }
    }



    public Medecin getMedecinAvecDisponibilites(Long id) {
        return medecinRepository.findById(id).orElseThrow(() -> new RuntimeException("Médecin non trouvé"));
    }

    public List<Medecin> creerMedecinsEnMasse(int nombre) {
        List<Medecin> medecins = new ArrayList<>();
        Random random = new Random();
        Specialite[] specialites = Specialite.values();

        for (int i = 1; i <= nombre; i++) {
            Medecin medecin = new Medecin();
            medecin.setNom("Dr. " + i);
            medecin.setSpecialite(specialites[random.nextInt(specialites.length)]);
            medecins.add(medecin);
        }
        return sauvegarderMedecins(medecins); // On utilise la méthode générique
    }

    // Méthode pour sauvegarder des médecins déjà créés
    public List<Medecin> sauvegarderMedecins(List<Medecin> medecins) {
        return medecinRepository.saveAll(medecins);
    }

    public List<Specialite> getAllSpecialites() {
        return Arrays.asList(Specialite.values());
    }
    public List<Medecin> getMedecinsParSpecialite(Specialite specialite) {
        return medecinRepository.findBySpecialite(specialite);
    }
}