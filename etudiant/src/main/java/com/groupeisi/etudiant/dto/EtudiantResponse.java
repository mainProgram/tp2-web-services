package com.groupeisi.etudiant.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EtudiantResponse {

    private Long id;

    private String nom;

    private String prenom;

    private LocalDate dateNaissance;

    private String sexe;

    private String matricule;
}
