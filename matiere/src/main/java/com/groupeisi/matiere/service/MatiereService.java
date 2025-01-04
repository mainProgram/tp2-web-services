package com.groupeisi.matiere.service;


import com.groupeisi.matiere.model.Matiere;
import com.groupeisi.matiere.repository.MatiereRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MatiereService {

    private final MatiereRepository matiereRepository;

    public MatiereService(MatiereRepository matiereRepository) {
        this.matiereRepository = matiereRepository;
    }

    public List<Matiere> getAllMatieres() {
        return matiereRepository.findAll();
    }
    public Matiere createMatiere(Matiere matiere) {
        return matiereRepository.save(matiere);
    }

    public Matiere getMatiere(int id) {
        return matiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matiere not found with id: " + id));
    }

    public boolean updateMatiere(Matiere matiere) {
        if (!matiereRepository.existsById(matiere.getId())) {
            return false;
        }
        matiereRepository.save(matiere);
        return true;
    }

    public boolean deleteMatiere(int id) {
        if (!matiereRepository.existsById(id)) {
            return false;
        }
        matiereRepository.deleteById(id);
        return true;
    }
}