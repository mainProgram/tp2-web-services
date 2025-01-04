package com.groupeisi.etudiant.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClasseResponse {

    private Long id;

    private String nom;

}
