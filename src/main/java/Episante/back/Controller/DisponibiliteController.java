package Episante.back.Controller;

import Episante.back.Models.Disponibilite;
import  Episante.back.Repository.DisponibiliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.List;

@RestController
@RequestMapping("/disponibilites")
public class DisponibiliteController {
    @Autowired
    private DisponibiliteRepository disponibiliteRepository;

    @GetMapping("/medecin/{medecinId}")
    public List<Disponibilite> getDisponibilitesByMedecin(@PathVariable Long medecinId) {
        return disponibiliteRepository.findByMedecinId(medecinId);
    }

    @PostMapping
    public Disponibilite createDisponibilite(@RequestBody Disponibilite disponibilite) {
        return disponibiliteRepository.save(disponibilite);
    }

    @PutMapping("/{id}")
    public Disponibilite updateDisponibilite(@PathVariable Long id, @RequestBody Disponibilite disponibiliteDetails) {
        Disponibilite disponibilite = disponibiliteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disponibilite not found"));
        disponibilite.setDebut(disponibiliteDetails.getDebut()); // Utilisation de setDebut
        disponibilite.setFin(disponibiliteDetails.getFin()); // Utilisation de setFin
        return disponibiliteRepository.save(disponibilite);
    }

    @DeleteMapping("/{id}")
    public void deleteDisponibilite(@PathVariable Long id) {
        disponibiliteRepository.deleteById(id);
    }
}