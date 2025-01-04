package com.groupeisi.classe_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class ClasseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;

    @ElementCollection
    private List<Long> professeursIds;

    @ElementCollection
    private List<Long> matieresIds;

    @ElementCollection
    private List<Long> etudiantsIds;

    public ClasseEntity(){}
    public ClasseEntity(String libelle, List<Long> professeursIds, List<Long> etudiantsIds, List<Long> matieresIds) {
        this.libelle = libelle;
        this.matieresIds = matieresIds;
        this.etudiantsIds = etudiantsIds;
        this.professeursIds = professeursIds;
    }
    public ClasseEntity(Long id, String libelle, List<Long> professeursIds, List<Long> etudiantsIds, List<Long> matieresIds) {
        this.id = id;
        this.libelle = libelle;
        this.matieresIds = matieresIds;
        this.etudiantsIds = etudiantsIds;
        this.professeursIds = professeursIds;
    }
}
