package Episante.back.Repository;

import Episante.back.Models.Disponibilite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DisponibiliteRepository extends JpaRepository<Disponibilite, Long> {
    @Query("SELECT d FROM Disponibilite d JOIN FETCH d.medecin")
    List<Disponibilite> findAllWithMedecin();

    @Query("SELECT d FROM Disponibilite d JOIN FETCH d.medecin WHERE d.medecin.id = :medecinId")
    List<Disponibilite> findByMedecinId(@Param("medecinId") Long medecinId);}
