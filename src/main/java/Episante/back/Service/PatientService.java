package Episante.back.Service;

import Episante.back.Repository.PatientRepository;
import Episante.back.Models.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientDao;

    public List<Patient> getAllPatients() {
        return patientDao.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientDao.findById(id).orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
    }

    public Patient savePatient(Patient patient) {
        return patientDao.save(patient);
    }



    public void deletePatient(Long id) {
        patientDao.deleteById(id);
    }
}
