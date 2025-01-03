package com.groupeisi.etudiant.model;

import com.groupeisi.etudiant.utils.enums.SexeEnum;
import com.groupeisi.etudiant.utils.enums.SexeEnumConstraint;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name = "etudiant")
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;

    private LocalDate dateNaissance;

    @SexeEnumConstraint
    private SexeEnum sexe;

    private String matricule;
}
