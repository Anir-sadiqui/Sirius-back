package Episante.back.Controller;

import com.github.javafaker.Faker;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Data
@RestController
@RequestMapping("/api/mock")
public class MockController {

    private static final Logger logger = LoggerFactory.getLogger(MockController.class);
    private final Faker faker = new Faker();

    @PostMapping("/generate-patients")
    public ResponseEntity<List<PatientCredentials>> generateMockPatients(
            @RequestParam int numberOfPatients) {

        List<PatientCredentials> patients = new ArrayList<>();

        for(int i = 0; i < numberOfPatients; i++) {
            String email = faker.internet().emailAddress();
            String password = faker.internet().password(8, 12);

            patients.add(new PatientCredentials(email, password));

            // Ici vous pourriez persister en base si nécessaire
            logger.info("Patient mock généré : {} / {}", email, password);
        }

        return ResponseEntity.ok(patients);
    }

   @Data
    public static class PatientCredentials {
        private String email;
        private String password;


       public PatientCredentials(String email, String password) {

           this.email = email;
           this.password = password;
       }
   }
}
