package Episante.back.Repository;

import Episante.back.Models.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPatientrepository extends ICrudRepository<Patient, Long> {

    List<Patient> findByNom(String name) ;

    List<Patient> findByPrenom(String prenom);

    boolean existsByEmail(String email);

    Patient findByEmail(String email);
}
