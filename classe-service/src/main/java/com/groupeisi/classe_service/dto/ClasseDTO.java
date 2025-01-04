package com.groupeisi.classe_service.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClasseDTO {
    private Long id;

    private String libelle;

    private List<Long> professeursIds;

    private List<Long> matieresIds;

    private List<Long> etudiantsIds;
}
