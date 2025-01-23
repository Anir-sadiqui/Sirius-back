package Episante.back;
import java.time.LocalDateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import Episante.back.Models.Medecin;
import Episante.back.Models.Disponibilite;
import Episante.back.Repository.MedecinRepository;
import Episante.back.Repository.DisponibiliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class EpisanteAPP  implements CommandLineRunner{


		@Autowired
		private MedecinRepository medecinRepository;

		@Autowired
		private DisponibiliteRepository disponibiliteRepository;

		public static void main(String[] args) {
			SpringApplication.run(EpisanteAPP.class, args);
		}

		@Override
		public void run(String... args) throws Exception {
			// Mock data for Medecin
			Medecin medecin = new Medecin();
			medecin.setNom("Dr. Smith");
			medecin.setSpecialite("Cardiologie");
			medecinRepository.save(medecin);

			// Mock data for Disponibilite
			Disponibilite disponibilite1 = new Disponibilite();
			disponibilite1.setDebut(LocalDateTime.of(2023, 10, 10, 9, 0));
			disponibilite1.setFin(LocalDateTime.of(2023, 10, 10, 12, 0));
			disponibilite1.setMedecin(medecin);
			disponibiliteRepository.save(disponibilite1);

			Disponibilite disponibilite2 = new Disponibilite();
			disponibilite2.setDebut(LocalDateTime.of(2023, 10, 11, 14, 0));
			disponibilite2.setFin(LocalDateTime.of(2023, 10, 11, 17, 0));
			disponibilite2.setMedecin(medecin);
			disponibiliteRepository.save(disponibilite2);
		}
	}