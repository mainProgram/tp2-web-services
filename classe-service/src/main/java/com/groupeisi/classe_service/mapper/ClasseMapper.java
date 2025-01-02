package com.groupeisi.classe_service.mapper;

import org.mapstruct.Mapper;

import com.groupeisi.classe_service.model.ClasseEntity;
import com.groupeisi.classe_service.dto.ClasseDTO;

@Mapper(componentModel = "spring")
public interface ClasseMapper extends EntityMapper<ClasseDTO, ClasseEntity> {}
