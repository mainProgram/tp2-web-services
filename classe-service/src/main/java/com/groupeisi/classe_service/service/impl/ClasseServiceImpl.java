package com.groupeisi.classe_service.service.impl;

import com.groupeisi.classe_service.dto.ClasseDTO;
import com.groupeisi.classe_service.dto.SaveClasseDTO;
import com.groupeisi.classe_service.mapper.ClasseMapper;
import com.groupeisi.classe_service.mapper.SaveClasseMapper;
import com.groupeisi.classe_service.model.ClasseEntity;
import com.groupeisi.classe_service.repository.ClasseRepository;
import com.groupeisi.classe_service.service.ClasseService;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClasseServiceImpl implements ClasseService {

    private final ClasseRepository classeRepository;

    private final ClasseMapper classeMapper;
    private final SaveClasseMapper saveClasseMapper;

    public ClasseServiceImpl(ClasseRepository classeRepository, ClasseMapper classeMapper,SaveClasseMapper saveClasseMapper) {
        this.classeRepository = classeRepository;
        this.classeMapper = classeMapper;
        this.saveClasseMapper = saveClasseMapper;
    }
    @Override
    public ClasseDTO save(SaveClasseDTO classeDTO) {
        ClasseEntity classe = saveClasseMapper.toEntity(classeDTO);
        classe = classeRepository.save(classe);
        return classeMapper.toDto(classe);
    }

    @Override
    public ClasseDTO update(ClasseDTO classeDTO) {
        return null;
    }

    @Override
    public List<ClasseDTO> findAll() {
        return classeRepository.findAll().stream().map(classeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public Optional<ClasseDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}
