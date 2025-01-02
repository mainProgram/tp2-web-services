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
@AllArgsConstructor
public class ClasseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;

    @ElementCollection
    private List<Long> professeursIds;

    public ClasseEntity(){}
    public ClasseEntity(String libelle, List<Long> professeursIds) {
        this.libelle = libelle;
        this.professeursIds = professeursIds;
    }
}
