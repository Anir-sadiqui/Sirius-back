package Episante.back.Repository;

import Episante.back.Models.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedecinDao extends JpaRepository<Medecin, Long> {
}
