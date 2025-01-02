package com.groupeisi.classe_service.service;


import com.groupeisi.classe_service.dto.ClasseDTO;
import com.groupeisi.classe_service.dto.SaveClasseDTO;

import java.util.List;
import java.util.Optional;

public interface ClasseService {

    ClasseDTO save(SaveClasseDTO classeDTO);

    ClasseDTO update(ClasseDTO classeDTO);

    List<ClasseDTO> findAll();

    Optional<ClasseDTO> findOne(Long id);

    void delete(Long id);
}