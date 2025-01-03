package com.groupeisi.classe.web.graphQL;

import com.groupeisi.classe.domain.Classe;
import com.groupeisi.classe.repository.ClasseRepository;
import com.groupeisi.classe.service.ClasseService;
import com.groupeisi.classe.service.dto.ClasseDTO;
import com.groupeisi.classe.service.dto.SaveClasseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/classes")
public class ClasseController {

    private static final Logger LOG = LoggerFactory.getLogger(ClasseController.class);
    private static final String ENTITY_NAME = "classe";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;
    private final ClasseService classeService;

    private final ClasseRepository classeRepository;

    public ClasseController(ClasseService classeService, ClasseRepository classeRepository) {
        this.classeService = classeService;
        this.classeRepository = classeRepository;
    }

    @QueryMapping
    public List<ClasseDTO> getAllClasses() {
        LOG.debug("REST request to get all Classes");
        return classeService.findAll();
    }

    @QueryMapping
    public Classe getClasseById(@Argument Long id) {
        LOG.debug("REST request to get one Class");
        return classeRepository.findById(id).get();
    }

    @MutationMapping
    public Classe createClasse(@Argument SaveClasseDTO saveClasseDTO){
        Classe classe = new Classe(saveClasseDTO.libelle(), saveClasseDTO.professeursIds());
        return classeRepository.save(classe);
    }

    @MutationMapping
    public Classe updateClasse(@Argument long id, @Argument SaveClasseDTO saveClasseDTO){
        Classe classe = new Classe(id, saveClasseDTO.libelle(), saveClasseDTO.professeursIds());
        return classeRepository.save(classe);
    }

    @MutationMapping
    public void deleteClasse(@Argument long id){
        classeRepository.delete(classeRepository.findById(id).get());
    }
}
