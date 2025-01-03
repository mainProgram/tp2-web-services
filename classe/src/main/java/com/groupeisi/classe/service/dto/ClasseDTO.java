package com.groupeisi.classe.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link com.groupeisi.classe.domain.Classe} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClasseDTO implements Serializable {

    private Long id;

    private String libelle;

    private Boolean status;


    private List<Long> professeursIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Long> getProfesseursIds() {
        return professeursIds;
    }

    public void setProfesseursIds(List<Long> professeursIds) {
        this.professeursIds = professeursIds;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClasseDTO)) {
            return false;
        }

        ClasseDTO classeDTO = (ClasseDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, classeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClasseDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
