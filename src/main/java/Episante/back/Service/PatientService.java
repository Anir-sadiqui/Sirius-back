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

    public List<Patient> getAllPatients() {
        return patientDao.findAll();
    }
}
