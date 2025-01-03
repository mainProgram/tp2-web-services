package com.groupeisi.etudiant.mapper;

import com.groupeisi.etudiant.dto.EtudiantRequest;
import com.groupeisi.etudiant.dto.EtudiantResponse;
import com.groupeisi.etudiant.model.EtudiantEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface EtudiantMapper {

    EtudiantEntity toEtudiantEntity(EtudiantRequest etudiant) ;
    EtudiantResponse toEtudiantResponse(EtudiantEntity etudiantEntity) ;
    List<EtudiantResponse> toEtudiantResponseList(List<EtudiantEntity> etudiantEntityList) ;
    List<EtudiantEntity> toEtudiantEntityList(List<EtudiantRequest> etudiantList) ;
}
