package Episante.back.Service;


import Episante.back.Models.Patient;
import Episante.back.Repository.IPatientrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private IPatientrepository patientDao;

    public List<Patient> getAllPatients() {return patientDao.findAll();}
    public List<Patient> getPatientByName(String name) {return patientDao.findByNom(name);}
    public List<Patient> getPatientByPrenom(String prenom) {return patientDao.findByPrenom(prenom);}
    public void addPatient(Patient patient) {patientDao.save(patient);}
    public void deletePatient(Patient patient) {patientDao.delete(patient);}



}
