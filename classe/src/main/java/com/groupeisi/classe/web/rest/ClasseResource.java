/*
package com.groupeisi.classe.web.rest;

import com.groupeisi.classe.repository.ClasseRepository;
import com.groupeisi.classe.service.ClasseService;
import com.groupeisi.classe.service.dto.ClasseDTO;
import com.groupeisi.classe.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

*/
/**
 * REST controller for managing {@link com.groupeisi.classe.domain.Classe}.
 *//*

@RestController
@RequestMapping("/api/classes")
public class ClasseResource {

    private static final Logger LOG = LoggerFactory.getLogger(ClasseResource.class);

    private static final String ENTITY_NAME = "classeClasse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClasseService classeService;

    private final ClasseRepository classeRepository;

    public ClasseResource(ClasseService classeService, ClasseRepository classeRepository) {
        this.classeService = classeService;
        this.classeRepository = classeRepository;
    }

    */
/**
     * {@code POST  /classes} : Create a new classe.
     *
     * @param classeDTO the classeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new classeDTO, or with status {@code 400 (Bad Request)} if the classe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     *//*

//    @PostMapping("")
//    public ResponseEntity<ClasseDTO> createClasse(@RequestBody ClasseDTO classeDTO) throws URISyntaxException {
//        LOG.debug("REST request to save Classe : {}", classeDTO);
//        if (classeDTO.getId() != null) {
//            throw new BadRequestAlertException("A new classe cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        classeDTO = classeService.save(classeDTO);
//        return ResponseEntity.created(new URI("/api/classes/" + classeDTO.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, classeDTO.getId().toString()))
//            .body(classeDTO);
//    }

    */
/**
     * {@code PUT  /classes/:id} : Updates an existing classe.
     *
     * @param id the id of the classeDTO to save.
     * @param classeDTO the classeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated classeDTO,
     * or with status {@code 400 (Bad Request)} if the classeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the classeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     *//*

    @PutMapping("/{id}")
    public ResponseEntity<ClasseDTO> updateClasse(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ClasseDTO classeDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Classe : {}, {}", id, classeDTO);
        if (classeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, classeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!classeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        classeDTO = classeService.update(classeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, classeDTO.getId().toString()))
            .body(classeDTO);
    }

    */
/**
     * {@code PATCH  /classes/:id} : Partial updates given fields of an existing classe, field will ignore if it is null
     *
     * @param id the id of the classeDTO to save.
     * @param classeDTO the classeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated classeDTO,
     * or with status {@code 400 (Bad Request)} if the classeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the classeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the classeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     *//*

    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ClasseDTO> partialUpdateClasse(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ClasseDTO classeDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Classe partially : {}, {}", id, classeDTO);
        if (classeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, classeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!classeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ClasseDTO> result = classeService.partialUpdate(classeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, classeDTO.getId().toString())
        );
    }

    */
/**
     * {@code GET  /classes} : get all the classes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of classes in body.
     *//*

    @GetMapping("")
    public List<ClasseDTO> getAllClasses() {
        LOG.debug("REST request to get all Classes");
        return classeService.findAll();
    }

    */
/**
     * {@code GET  /classes/:id} : get the "id" classe.
     *
     * @param id the id of the classeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the classeDTO, or with status {@code 404 (Not Found)}.
     *//*

    @GetMapping("/{id}")
    public ResponseEntity<ClasseDTO> getClasse(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Classe : {}", id);
        Optional<ClasseDTO> classeDTO = classeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(classeDTO);
    }

    */
/**
     * {@code DELETE  /classes/:id} : delete the "id" classe.
     *
     * @param id the id of the classeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     *//*

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClasse(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Classe : {}", id);
        classeService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
*/
