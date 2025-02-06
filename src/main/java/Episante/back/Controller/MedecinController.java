package Episante.back.Controller;

import Episante.back.Models.Disponibilite;
import Episante.back.Models.JourSemaine;
import Episante.back.Models.Medecin;
import Episante.back.Models.Specialite;
import Episante.back.Repository.DisponibiliteRepository;
import Episante.back.Repository.MedecinRepository;
import Episante.back.Service.MedecinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;


//import org.springframework.web.bind.annotation.CrossOrigin;
////
//// Ajoutez cette annotation au-dessus de votre contrôleur
//@CrossOrigin(origins = "http://localhost:5173") // Port de votre frontend React

@RestController
@RequestMapping("/api/medecins")
public class MedecinController {
    private final MedecinService medecinService;
    private final MedecinRepository medecinRepository;
    private final DisponibiliteRepository disponibiliteRepository;
    private final Random random = new Random();

    // Liste de prénoms et noms de famille pour générer des noms aléatoires
    private static final String[] PRENOMS = {"Jean", "Marie", "Paul", "Sophie", "Lucas", "Emma", "Hugo", "Léa", "Nina", "Alex"};
    private static final String[] NOMS = {"Dupont", "Durand", "Martin", "Bernard", "Morel", "Simon", "Lefebvre", "Girard", "Mercier", "Roux"};

    public MedecinController(MedecinService medecinService, MedecinRepository medecinRepository, DisponibiliteRepository disponibiliteRepository) {
        this.medecinService = medecinService;
        this.medecinRepository = medecinRepository;
        this.disponibiliteRepository = disponibiliteRepository;
    }

    @GetMapping("/{id}")
    public Medecin getMedecinAvecDisponibilites(@PathVariable Long id) {
        return medecinService.getMedecinAvecDisponibilites(id);
    }

    // Endpoint pour créer un médecin
    @PostMapping("/creer-en-masse?nombre=10")
    public Medecin creerMedecin(@RequestBody Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @GetMapping("/specialite/{specialite}")
    public List<Medecin> getMedecinsParSpecialite(@PathVariable Specialite specialite) {
        return medecinRepository.findBySpecialite(specialite);
    }

    @GetMapping("/specialites")
    public ResponseEntity<List<String>> getAllSpecialites() {
        List<String> specialites = Arrays.stream(Specialite.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return ResponseEntity.ok(specialites);
    }
    @PostMapping("/creer-en-masse")
    public ResponseEntity<List<Medecin>> creerMedecinsEnMasse(@RequestParam int nombre) {
        List<Medecin> medecins = new ArrayList<>();
        Specialite[] specialites = Specialite.values(); // Enum des spécialités

        for (int i = 0; i < nombre; i++) {
            Medecin medecin = new Medecin();
            medecin.setNom(genererNomAleatoire()); // Génération d'un nom aléatoire
            medecin.setSpecialite(specialites[random.nextInt(specialites.length)]); // Spécialité aléatoire
            medecins.add(medecin);
        }

        List<Medecin> savedMedecins = medecinService.sauvegarderMedecins(medecins);
        return ResponseEntity.ok(savedMedecins);
    }

    // Méthode pour générer un nom aléatoire
    private String genererNomAleatoire() {
        String prenom = PRENOMS[random.nextInt(PRENOMS.length)];
        String nom = NOMS[random.nextInt(NOMS.length)];
        return "Dr. " + prenom + " " + nom;
    }

    @PostMapping("/generer-disponibilites")
    public ResponseEntity<String> genererDisponibilites() {
        medecinService.genererDisponibilitesAleatoires();
        return ResponseEntity.ok("Disponibilités générées avec succès !");
    }

    // 🔹 API avec filtres optionnels (médecin et/ou jour)
    @GetMapping("/disponibilites")
    public ResponseEntity<List<Disponibilite>> getDisponibilites(
            @RequestParam(required = false) Long medecinId,
            @RequestParam(required = false) JourSemaine jour) {

        List<Disponibilite> disponibilites;

        if (medecinId != null && jour != null) {
            // Filtrer par médecin et jour
            disponibilites = disponibiliteRepository.findByMedecinIdAndJour(medecinId, jour);
        } else if (medecinId != null) {
            // Filtrer par médecin uniquement
            disponibilites = disponibiliteRepository.findByMedecinId(medecinId);
        } else if (jour != null) {
            // Filtrer par jour uniquement
            disponibilites = disponibiliteRepository.findByJour(jour);
        } else {
            // Aucun filtre, récupérer toutes les disponibilités
            disponibilites = disponibiliteRepository.findAll();
        }

        return ResponseEntity.ok(disponibilites);
    }

@GetMapping("/disponibilitess")
public ResponseEntity<List<Map<String, Object>>> getDisponibilites(
        @RequestParam(required = false) Long medecinId) {

    List<Disponibilite> disponibilites;

    if (medecinId != null) {
        disponibilites = disponibiliteRepository.findByMedecinId(medecinId);
    } else {
        disponibilites = disponibiliteRepository.findAll();
    }

    // 🔥 Transformation des disponibilités avec date au format "YYYY-MM-DD"
    List<Map<String, Object>> disponibilitesFormatees = disponibilites.stream().map(dispo -> {
        Map<String, Object> map = new HashMap<>();
        map.put("id", dispo.getId());
        map.put("date", convertJourToDate(dispo.getJour())); // Nouvelle fonction pour convertir en date
        map.put("periode", dispo.getPeriode());
        return map;
    }).collect(Collectors.toList());

    return ResponseEntity.ok(disponibilitesFormatees);
}

// 🔹 Convertir JourSemaine en une vraie date au format "YYYY-MM-DD"
private String convertJourToDate(JourSemaine jour) {
    LocalDate today = LocalDate.now();
    int targetDay = jour.ordinal() + 1; // Lundi = 1, Mardi = 2, etc.

    // Trouver le prochain jour correspondant
    LocalDate nextDate = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.of(targetDay)));
    return nextDate.toString(); // Format "YYYY-MM-DD"
}
}
