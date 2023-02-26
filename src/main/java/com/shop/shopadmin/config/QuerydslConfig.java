package com.shop.shopadmin.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.shopadmin.code.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class QuerydslConfig {
    @PersistenceContext(unitName = Constants.unitName)
    private EntityManager entityManager;

    @Primary
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
