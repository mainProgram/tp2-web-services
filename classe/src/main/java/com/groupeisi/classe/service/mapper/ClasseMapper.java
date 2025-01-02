package com.groupeisi.classe.service.mapper;

import com.groupeisi.classe.domain.Classe;
import com.groupeisi.classe.service.dto.ClasseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Classe} and its DTO {@link ClasseDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClasseMapper extends EntityMapper<ClasseDTO, Classe> {}
