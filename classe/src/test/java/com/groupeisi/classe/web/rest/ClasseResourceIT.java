package com.groupeisi.classe.web.rest;

import static com.groupeisi.classe.domain.ClasseAsserts.*;
import static com.groupeisi.classe.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.groupeisi.classe.IntegrationTest;
import com.groupeisi.classe.domain.Classe;
import com.groupeisi.classe.repository.ClasseRepository;
import com.groupeisi.classe.service.dto.ClasseDTO;
import com.groupeisi.classe.service.mapper.ClasseMapper;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ClasseResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClasseResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_STATUS = false;
    private static final Boolean UPDATED_STATUS = true;

    private static final String ENTITY_API_URL = "/api/classes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private ClasseMapper classeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClasseMockMvc;

    private Classe classe;

    private Classe insertedClasse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Classe createEntity() {
        return new Classe().libelle(DEFAULT_LIBELLE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Classe createUpdatedEntity() {
        return new Classe().libelle(UPDATED_LIBELLE);
    }

    @BeforeEach
    public void initTest() {
        classe = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedClasse != null) {
            classeRepository.delete(insertedClasse);
            insertedClasse = null;
        }
    }

    @Test
    @Transactional
    void createClasse() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Classe
        ClasseDTO classeDTO = classeMapper.toDto(classe);
        var returnedClasseDTO = om.readValue(
            restClasseMockMvc
                .perform(post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(classeDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ClasseDTO.class
        );

        // Validate the Classe in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedClasse = classeMapper.toEntity(returnedClasseDTO);
        assertClasseUpdatableFieldsEquals(returnedClasse, getPersistedClasse(returnedClasse));

        insertedClasse = returnedClasse;
    }

    @Test
    @Transactional
    void createClasseWithExistingId() throws Exception {
        // Create the Classe with an existing ID
        classe.setId(1L);
        ClasseDTO classeDTO = classeMapper.toDto(classe);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClasseMockMvc
            .perform(post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(classeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Classe in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllClasses() throws Exception {
        // Initialize the database
        insertedClasse = classeRepository.saveAndFlush(classe);

        // Get all the classeList
        restClasseMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(classe.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    @Test
    @Transactional
    void getClasse() throws Exception {
        // Initialize the database
        insertedClasse = classeRepository.saveAndFlush(classe);

        // Get the classe
        restClasseMockMvc
            .perform(get(ENTITY_API_URL_ID, classe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(classe.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    void getNonExistingClasse() throws Exception {
        // Get the classe
        restClasseMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingClasse() throws Exception {
        // Initialize the database
        insertedClasse = classeRepository.saveAndFlush(classe);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the classe
        Classe updatedClasse = classeRepository.findById(classe.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedClasse are not directly saved in db
        em.detach(updatedClasse);
        updatedClasse.libelle(UPDATED_LIBELLE);
        ClasseDTO classeDTO = classeMapper.toDto(updatedClasse);

        restClasseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, classeDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(classeDTO))
            )
            .andExpect(status().isOk());

        // Validate the Classe in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedClasseToMatchAllProperties(updatedClasse);
    }

    @Test
    @Transactional
    void putNonExistingClasse() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        classe.setId(longCount.incrementAndGet());

        // Create the Classe
        ClasseDTO classeDTO = classeMapper.toDto(classe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClasseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, classeDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(classeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Classe in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchClasse() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        classe.setId(longCount.incrementAndGet());

        // Create the Classe
        ClasseDTO classeDTO = classeMapper.toDto(classe);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClasseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(classeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Classe in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamClasse() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        classe.setId(longCount.incrementAndGet());

        // Create the Classe
        ClasseDTO classeDTO = classeMapper.toDto(classe);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClasseMockMvc
            .perform(put(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(classeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Classe in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateClasseWithPatch() throws Exception {
        // Initialize the database
        insertedClasse = classeRepository.saveAndFlush(classe);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the classe using partial update
        Classe partialUpdatedClasse = new Classe();
        partialUpdatedClasse.setId(classe.getId());

        partialUpdatedClasse.libelle(UPDATED_LIBELLE);

        restClasseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClasse.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedClasse))
            )
            .andExpect(status().isOk());

        // Validate the Classe in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertClasseUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedClasse, classe), getPersistedClasse(classe));
    }

    @Test
    @Transactional
    void fullUpdateClasseWithPatch() throws Exception {
        // Initialize the database
        insertedClasse = classeRepository.saveAndFlush(classe);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the classe using partial update
        Classe partialUpdatedClasse = new Classe();
        partialUpdatedClasse.setId(classe.getId());

        partialUpdatedClasse.libelle(UPDATED_LIBELLE);

        restClasseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClasse.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedClasse))
            )
            .andExpect(status().isOk());

        // Validate the Classe in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertClasseUpdatableFieldsEquals(partialUpdatedClasse, getPersistedClasse(partialUpdatedClasse));
    }

    @Test
    @Transactional
    void patchNonExistingClasse() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        classe.setId(longCount.incrementAndGet());

        // Create the Classe
        ClasseDTO classeDTO = classeMapper.toDto(classe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClasseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, classeDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(classeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Classe in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchClasse() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        classe.setId(longCount.incrementAndGet());

        // Create the Classe
        ClasseDTO classeDTO = classeMapper.toDto(classe);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClasseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(classeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Classe in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamClasse() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        classe.setId(longCount.incrementAndGet());

        // Create the Classe
        ClasseDTO classeDTO = classeMapper.toDto(classe);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClasseMockMvc
            .perform(
                patch(ENTITY_API_URL).with(csrf()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(classeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Classe in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteClasse() throws Exception {
        // Initialize the database
        insertedClasse = classeRepository.saveAndFlush(classe);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the classe
        restClasseMockMvc
            .perform(delete(ENTITY_API_URL_ID, classe.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return classeRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Classe getPersistedClasse(Classe classe) {
        return classeRepository.findById(classe.getId()).orElseThrow();
    }

    protected void assertPersistedClasseToMatchAllProperties(Classe expectedClasse) {
        assertClasseAllPropertiesEquals(expectedClasse, getPersistedClasse(expectedClasse));
    }

    protected void assertPersistedClasseToMatchUpdatableProperties(Classe expectedClasse) {
        assertClasseAllUpdatablePropertiesEquals(expectedClasse, getPersistedClasse(expectedClasse));
    }
}
