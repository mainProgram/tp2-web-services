package com.groupeisi.etudiant.service;

import com.groupeisi.etudiant.dto.EtudiantRequest;
import com.groupeisi.etudiant.dto.EtudiantResponse;

import java.util.List;
import java.util.Optional;

public class EtudiantServiceImpl implements EtudiantService{

    @Override
    public EtudiantResponse saveEtudiant(EtudiantRequest request) {
        return null;
    }

    @Override
    public List<EtudiantResponse> getAllEtudiants() {
        return null;
    }

    @Override
    public Optional<EtudiantResponse> getEtudiantById(String id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteEtudiant(String id) {
        return false;
    }

    @Override
    public Optional<EtudiantResponse> updateEtudiant(String id, EtudiantRequest request) {
        return Optional.empty();
    }
}
