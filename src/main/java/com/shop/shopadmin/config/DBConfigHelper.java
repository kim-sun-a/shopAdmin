package com.shop.shopadmin.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

public class DBConfigHelper {
    public static HikariDataSource getDataSource(HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

    public static LocalContainerEntityManagerFactoryBean getEntityManagerFactory(String unitName, Environment env, DataSource dataSource, String[] entityScan) {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setDatabasePlatform(env.getProperty("spring.jpa.database-platform"));
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan(entityScan);
        factory.setJpaVendorAdapter(adapter);
        factory.setPersistenceUnitName(unitName);
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        jpaProperties.put("hibernate.show-sql"    , Boolean.getBoolean(env.getProperty("spring.jpa.show-sql")));
        jpaProperties.put("hibernate.format_sql"  , env.getProperty("spring.jpa.properties.hibernate.format_sql"));
        factory.setJpaProperties(jpaProperties);
        return factory;
    }

    public static PlatformTransactionManager getTransactionManager(EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }
}
