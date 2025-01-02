package com.groupeisi.classe.domain;

import static com.groupeisi.classe.domain.ClasseTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.groupeisi.classe.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClasseTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Classe.class);
        Classe classe1 = getClasseSample1();
        Classe classe2 = new Classe();
        assertThat(classe1).isNotEqualTo(classe2);

        classe2.setId(classe1.getId());
        assertThat(classe1).isEqualTo(classe2);

        classe2 = getClasseSample2();
        assertThat(classe1).isNotEqualTo(classe2);
    }
}
