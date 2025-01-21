package Episante.back.Controller;

import Episante.back.Repository.IPatientrepository;
import Episante.back.Models.Patient;
import Episante.back.Service.PatientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/patients")
public class PatientController {

    // using loggers better than Sout , for debuging , errors , ....
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);


    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients() {
        logger.info("Get all patients");
        return patientService.getAllPatients();
    }


    //using the ResponseEntity to see the status of the request
    @PostMapping
    public ResponseEntity<Patient> createPatient(@Valid @RequestBody Patient patient) {
        patientService.addPatient(patient);
        logger.info("Patient created successfully: {}", patient.getNom() + " " + patient.getPrenom());
        return ResponseEntity.status(HttpStatus.CREATED).body(patient);
    }

// old version
//    @PutMapping("/{id}")
//    public Patient updatePatient( @PathVariable Long id , @RequestBody Patient patientDetails) {
//        Patient patient = patientDao.findById(id).orElseThrow(() -> new RuntimeException("Patient not found with id: " + id)) ;
//
//        patient.setNom(patientDetails.getNom());
//        patient.setPrenom(patientDetails.getPrenom());
//        patient.setAge(patientDetails.getAge());
//        patient.setPoids(patientDetails.getPoids());
//        patient.setSexe(patientDetails.getSexe());
//
//        System.out.println("Patient updated successfully");
//        return patientDao.save(patient);
//
//    }
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id , @Valid @RequestBody Patient patientDetails) {
        Patient patient = patientService.findById(id).orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        BeanUtils.copyProperties(patientDetails, patient); // with copyProperties method you just copy the propreties of your Patient to avoid setters
        Patient updatedPatient = patientDao.save(patient);
        logger.info("Patient updated successfully: {}", updatedPatient.getId());
        return ResponseEntity.status(HttpStatus.OK).body(updatedPatient);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> deletePatient(@PathVariable Long id) {
        if(!patientDao.existsById(id)) {
            logger.error("Patient not found with id: " + id);
        }
        patientDao.deleteById(id);
        logger.info("Patient deleted successfully: {}", id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
