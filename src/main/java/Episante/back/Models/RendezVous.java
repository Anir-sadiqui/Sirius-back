package Episante.back.Models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
public class RendezVous {


    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Entity
    public class Rdv {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private LocalDateTime dateHeure;

        @ManyToOne
        @JoinColumn(name = "medecin_id")
        private Medecin medecin;

        @ManyToOne
        @JoinColumn(name = "patient_id")
        private Patient patient;

        // Getters and Setters
    }

}

