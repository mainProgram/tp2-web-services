package com.groupeisi.etudiant;

import com.groupeisi.etudiant.config.AsyncSyncConfiguration;
import com.groupeisi.etudiant.config.EmbeddedSQL;
import com.groupeisi.etudiant.config.JacksonConfiguration;
import com.groupeisi.etudiant.config.TestSecurityConfiguration;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = { EtudiantApp.class, JacksonConfiguration.class, AsyncSyncConfiguration.class, TestSecurityConfiguration.class })
@EmbeddedSQL
public @interface IntegrationTest {
}
