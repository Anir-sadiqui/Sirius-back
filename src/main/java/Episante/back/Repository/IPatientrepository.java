package Episante.back.Repository;

import Episante.back.Models.Patient;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientrepository extends ICrudRepository<Patient, Long> {

}
