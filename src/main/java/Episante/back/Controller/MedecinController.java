package Episante.back.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Episante.back.Models.Medecin;
import Episante.back.Repository.MedecinRepository;
import java.util.List;

@RestController
@RequestMapping("/medecins")
public class MedecinController {
    @Autowired
    private MedecinRepository medecinRepository;

    @GetMapping
    public List<Medecin> getAllMedecins() {
        return medecinRepository.findAll();
    }

    @PostMapping
    public Medecin createMedecin(@RequestBody Medecin medecin) {
        return medecinRepository.save(medecin);
    }
}