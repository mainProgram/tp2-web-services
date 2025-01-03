package com.groupeisi.etudiant.dto;

import com.groupeisi.etudiant.utils.enums.SexeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EtudiantResponse {

    private Long id;

    private String nom;

    private String prenom;

    private LocalDate dateNaissance;

    private SexeEnum sexe;

    private String matricule;
}
