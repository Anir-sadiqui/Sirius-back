package Episante.back.DAO;

import Episante.back.Entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedecinDao extends JpaRepository<Medecin, Long> {
}
