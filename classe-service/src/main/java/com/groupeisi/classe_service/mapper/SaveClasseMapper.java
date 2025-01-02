package com.groupeisi.classe_service.mapper;

import com.groupeisi.classe_service.dto.SaveClasseDTO;
import com.groupeisi.classe_service.model.ClasseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaveClasseMapper extends EntityMapper<SaveClasseDTO, ClasseEntity> {}
