package com.groupeisi.classe_service.controller;

import com.groupeisi.classe_service.dto.ClasseDTO;
import com.groupeisi.classe_service.dto.SaveClasseDTO;
import com.groupeisi.classe_service.model.ClasseEntity;
import com.groupeisi.classe_service.repository.ClasseRepository;
import com.groupeisi.classe_service.service.ClasseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/classes")
public class ClasseController {

    private static final Logger LOG = LoggerFactory.getLogger(ClasseController.class);

    private final ClasseRepository classeRepository;

    public ClasseController(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    @QueryMapping
    public List<ClasseEntity> getAllClasses() {
        LOG.debug("REST request to get all Classes");
        return classeRepository.findAll();
    }

    @QueryMapping
    public ClasseEntity getClasseById(@Argument Long id) {
        LOG.debug("REST request to get one Class");
        return classeRepository.findById(id).get();
    }

    @MutationMapping
    public ClasseEntity createClasse(@Argument SaveClasseDTO saveClasseDTO){
        ClasseEntity classe = new ClasseEntity(saveClasseDTO.libelle(), saveClasseDTO.professeursIds());
        return classeRepository.save(classe);
    }

    @MutationMapping
    public ClasseEntity updateClasse(@Argument long id, @Argument SaveClasseDTO saveClasseDTO){
        ClasseEntity classe = new ClasseEntity(id, saveClasseDTO.libelle(), saveClasseDTO.professeursIds());
        return classeRepository.save(classe);
    }

    @MutationMapping
    public void deleteClasse(@Argument long id){
        classeRepository.delete(classeRepository.findById(id).get());
    }
}
