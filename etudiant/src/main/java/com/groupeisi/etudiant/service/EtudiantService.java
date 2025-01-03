package com.groupeisi.etudiant.service;

import com.groupeisi.etudiant.dto.EtudiantRequest;
import com.groupeisi.etudiant.dto.EtudiantResponse;

import java.util.List;
import java.util.Optional;

public interface EtudiantService {

    EtudiantResponse saveEtudiant(EtudiantRequest request);

    List<EtudiantResponse> getAllEtudiants();

    Optional<EtudiantResponse> getEtudiantById(String id);

    boolean deleteEtudiant(String id);

    Optional<EtudiantResponse> updateEtudiant(String id, EtudiantRequest request);

}
