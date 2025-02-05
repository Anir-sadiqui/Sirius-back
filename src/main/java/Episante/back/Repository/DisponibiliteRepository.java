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

    // Récupérer les disponibilités d’un médecin spécifique
        List<Disponibilite> findByMedecinId(Long medecinId);

        // Récupérer les disponibilités d’un jour spécifique
        List<Disponibilite> findByJour(JourSemaine jour);

        // Récupérer les disponibilités d’un médecin pour un jour spécifique
        List<Disponibilite> findByMedecinIdAndJour(Long medecinId, JourSemaine jour);
    }


