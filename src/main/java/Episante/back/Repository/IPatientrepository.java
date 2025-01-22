package Episante.back.Repository;


import Episante.back.Models.Patient;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientrepository extends org.springframework.data.jpa.repository.JpaRepository<Patient, Long> {

}
