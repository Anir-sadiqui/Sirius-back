package Episante.back.Repository;
import Episante.back.Models.Disponibilite;
import Episante.back.Models.JourSemaine;
import Episante.back.Models.Medecin;
import Episante.back.Models.Periode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface DisponibiliteRepository extends JpaRepository<Disponibilite, Long> {

    // Vérifier si une disponibilité existe déjà pour un médecin, un jour et une période
    Optional<Disponibilite> findByMedecinAndJourAndPeriode(Medecin medecin, JourSemaine jour, Periode periode);

        List<Disponibilite> findByMedecinId(Long medecinId);
        List<Disponibilite> findByJour(JourSemaine jour);
        List<Disponibilite> findByMedecinIdAndJour(Long medecinId, JourSemaine jour);
    }


