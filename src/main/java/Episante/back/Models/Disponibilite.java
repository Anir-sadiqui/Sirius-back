package Episante.back.Models;

import Episante.back.Models.Medecin;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.Data;

@Data
@Entity
public class Disponibilite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private JourSemaine jour;
    @Enumerated(EnumType.STRING)
    private Periode periode;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;

}