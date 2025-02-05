package Episante.back.Models;

import Episante.back.Models.RendezVous;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String prenom;
    private int age;
    private double poids;
    private Sexe sexe;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<RendezVous> rdvs;
}

