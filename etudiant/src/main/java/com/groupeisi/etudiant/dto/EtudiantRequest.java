package com.groupeisi.etudiant.dto;

import com.groupeisi.etudiant.utils.enums.SexeEnum;
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

    private SexeEnum sexe;

    private String matricule;
}
