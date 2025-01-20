package Episante.back.Repository;

import Episante.back.Models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientDao extends JpaRepository<Patient, Long> {
}
