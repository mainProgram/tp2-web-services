package com.groupeisi.classe.service;

import com.groupeisi.classe.service.dto.ClasseDTO;
import com.groupeisi.classe.service.dto.SaveClasseDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.groupeisi.classe.domain.Classe}.
 */
public interface ClasseService {
    /**
     * Save a classe.
     *
     * @param classeDTO the entity to save.
     * @return the persisted entity.
     */
    ClasseDTO save(ClasseDTO classeDTO);

    /**
     * Updates a classe.
     *
     * @param classeDTO the entity to update.
     * @return the persisted entity.
     */
    ClasseDTO update(ClasseDTO classeDTO);

    /**
     * Partially updates a classe.
     *
     * @param classeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ClasseDTO> partialUpdate(ClasseDTO classeDTO);

    /**
     * Get all the classes.
     *
     * @return the list of entities.
     */
    List<ClasseDTO> findAll();

    /**
     * Get the "id" classe.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClasseDTO> findOne(Long id);

    /**
     * Delete the "id" classe.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
