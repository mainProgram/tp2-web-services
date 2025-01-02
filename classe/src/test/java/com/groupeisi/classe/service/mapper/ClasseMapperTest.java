package com.groupeisi.classe.service.mapper;

import static com.groupeisi.classe.domain.ClasseAsserts.*;
import static com.groupeisi.classe.domain.ClasseTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClasseMapperTest {

    private ClasseMapper classeMapper;

    @BeforeEach
    void setUp() {
        classeMapper = new ClasseMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getClasseSample1();
        var actual = classeMapper.toEntity(classeMapper.toDto(expected));
        assertClasseAllPropertiesEquals(expected, actual);
    }
}
