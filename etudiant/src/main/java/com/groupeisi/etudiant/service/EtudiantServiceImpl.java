package com.groupeisi.etudiant.service;

import com.groupeisi.etudiant.dto.EtudiantRequest;
import com.groupeisi.etudiant.dto.EtudiantResponse;
import com.groupeisi.etudiant.mapper.EtudiantMapper;
import com.groupeisi.etudiant.model.EtudiantEntity;
import com.groupeisi.etudiant.repository.EtudiantRepository;
import com.groupeisi.etudiant.utils.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EtudiantServiceImpl implements EtudiantService{

    private final EtudiantMapper etudiantMapper;
    private final EtudiantRepository etudiantRepository;

    @Override
    public EtudiantResponse saveEtudiant(EtudiantRequest request) {
        EtudiantEntity etudiantEntity = etudiantMapper.toEtudiantEntity(request);
        return etudiantMapper.toEtudiantResponse(etudiantRepository.save(etudiantEntity));
    }

    @Override
    public List<EtudiantResponse> getAllEtudiants() {
        return etudiantMapper.toEtudiantResponseList(etudiantRepository.findAll());
    }

    @Override
    public Optional<EtudiantResponse> getEtudiantById(Long id) {
        return Optional.ofNullable(etudiantRepository.findById(id)
                .map(etudiantMapper::toEtudiantResponse)
                .orElseThrow(() -> new EntityNotFoundException("L'étudiant avec l'ID " + id + " n'existe pas.")));
    }

    @Override
    public boolean deleteEtudiant(Long id) {
        Optional<EtudiantEntity> etudiantEntity;
        try {
            etudiantEntity = etudiantRepository.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("L'étudiant avec l'ID " + id + " n'existe pas.");
        }
        if (etudiantEntity.isPresent()){
            etudiantRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<EtudiantResponse> updateEtudiant(Long id, EtudiantRequest etudiantRequest) {
        return Optional.ofNullable(etudiantRepository.findById(id)
                .map(etudiant -> {
                    etudiant.setNom(etudiantRequest.getNom());
                    etudiant.setPrenom(etudiantRequest.getPrenom());
                    etudiant.setDateNaissance(etudiantRequest.getDateNaissance());
                    etudiant.setSexe(etudiantRequest.getSexe());
                    EtudiantEntity updatedEtudiant = etudiantRepository.save(etudiant);
                    return etudiantMapper.toEtudiantResponse(updatedEtudiant);
                })
                .orElseThrow(() -> new EntityNotFoundException("L'etudiant avec l'ID " + id + " n'existe pas.")));

    }
}
