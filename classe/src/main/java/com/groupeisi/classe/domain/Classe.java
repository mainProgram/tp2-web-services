package com.groupeisi.classe.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Classe.
 */
@Entity
@Table(name = "classe")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class
Classe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @ElementCollection
    private List<Long> professeursIds;


    public List<Long> getProfesseursIds() {
        return professeursIds;
    }

    public void setProfesseursIds(List<Long> professeursIds) {
        this.professeursIds = professeursIds;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Classe id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public Classe libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Classe)) {
            return false;
        }
        return getId() != null && getId().equals(((Classe) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Classe{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }

    public Classe() {
    }

    public Classe(Long id, String libelle, List<Long> professeursIds) {
        this.id = id;
        this.libelle = libelle;
        this.professeursIds = professeursIds;
    }

    public Classe(String libelle, List<Long> professeursIds) {
        this.libelle = libelle;
        this.professeursIds = professeursIds;
    }
}
