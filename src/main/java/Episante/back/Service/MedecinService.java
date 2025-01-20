package Episante.back.Service;

import Episante.back.Repository.IMedecinDao;
import Episante.back.Models.Medecin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedecinService {

    @Autowired
    private IMedecinDao medecinDao;

    public List<Medecin> getAllMedecins() {
        return medecinDao.findAll();
    }

    public Medecin getMedecinById(Long id) {
        return medecinDao.findById(id).orElseThrow(() -> new RuntimeException("Medecin not found with id: " + id));
    }

    public Medecin saveMedecin(Medecin medecin) {
        return medecinDao.save(medecin);
    }

    public Medecin updateMedecin(Long id, Medecin medecinDetails) {
        Medecin medecin = medecinDao.findById(id).orElseThrow(() -> new RuntimeException("Medecin not found with id: " + id));
        medecin.setNom(medecinDetails.getNom());
        medecin.setPrenom(medecinDetails.getPrenom());
        medecin.setSpecialite(medecinDetails.getSpecialite());
        return medecinDao.save(medecin);
    }

    public void deleteMedecin(Long id) {
        medecinDao.deleteById(id);
    }
}
