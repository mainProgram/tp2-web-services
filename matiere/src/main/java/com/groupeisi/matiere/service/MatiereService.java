package com.groupeisi.matiere.service;


import com.groupeisi.matiere.model.Matiere;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatiereService {
    private final Map<Integer, Matiere> matieres = new HashMap<>();
    private int nextId = 1;

    public List<Matiere> getAllMatieres() {
        if (matieres == null) {
            return null;
        }
        return new ArrayList<Matiere>(matieres.values());
    }
    public Matiere createMatiere(Matiere matiere) {
        matiere.setId(nextId++);
        matieres.put(matiere.getId(), matiere);
        return matiere;
    }

    public Matiere getMatiere(int id) {
        return matieres.get(id);
    }


    public boolean updateMatiere(Matiere matiere) {
        if(matieres.containsKey(matiere.getId())){
            matieres.put(matiere.getId(), matiere);
            return true;
        }
        return false;
    }


    public boolean deleteMatiere(int id) {
        if (matieres.containsKey(id)) {
            matieres.remove(id);
            return true;
        }
        return false;
    }
}