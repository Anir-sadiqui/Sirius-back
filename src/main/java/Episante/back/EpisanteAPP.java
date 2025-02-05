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

		}
	}




