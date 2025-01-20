package Episante.back.Service;

import Episante.back.DAO.IPatientDao;
import Episante.back.Entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private IPatientDao patientDao;

    public List<Patient> getAllPatients() {
        return patientDao.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientDao.findById(id).orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
    }

    public Patient savePatient(Patient patient) {
        return patientDao.save(patient);
    }

    public Patient updatePatient(Long id, Patient patientDetails) {
        Patient patient = patientDao.findById(id).orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        patient.setNom(patientDetails.getNom());
        patient.setPrenom(patientDetails.getPrenom());
        patient.setAge(patientDetails.getAge());
        patient.setPoids(patientDetails.getPoids());
        return patientDao.save(patient);
    }

    public void deletePatient(Long id) {
        patientDao.deleteById(id);
    }
}
