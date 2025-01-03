package com.groupeisi.etudiant.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
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

    private String sexe;

    private String matricule;
}
