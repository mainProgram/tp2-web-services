package com.groupeisi.matiere;

import com.groupeisi.matiere.config.AsyncSyncConfiguration;
import com.groupeisi.matiere.config.EmbeddedSQL;
import com.groupeisi.matiere.config.JacksonConfiguration;
import com.groupeisi.matiere.config.TestSecurityConfiguration;
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
@SpringBootTest(classes = { MatiereApp.class, JacksonConfiguration.class, AsyncSyncConfiguration.class, TestSecurityConfiguration.class })
@EmbeddedSQL
public @interface IntegrationTest {
}
