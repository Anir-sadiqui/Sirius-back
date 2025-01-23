package Episante.back.Repository;

import Episante.back.Models.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPatientrepository extends org.springframework.data.jpa.repository.JpaRepository<Patient, Long> {

    List<Patient> findByNom(String name) ;

    List<Patient> findByPrenom(String prenom);

    boolean existsByEmail(String email);

    Patient findByEmail(String email);
}
