package com.groupeisi.etudiant.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EtudiantRequest {

    private String nom;

    private String prenom;

    private LocalDate dateNaissance;

    private String sexe;

    private String matricule;
}
