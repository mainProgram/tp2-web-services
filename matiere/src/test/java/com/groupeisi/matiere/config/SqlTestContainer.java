package com.groupeisi.matiere.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.testcontainers.containers.JdbcDatabaseContainer;

public interface SqlTestContainer extends InitializingBean, DisposableBean {
    JdbcDatabaseContainer<?> getTestContainer();
}
