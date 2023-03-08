package com.shop.shopadmin.config;

import com.shop.shopadmin.code.Constants;
import com.shop.shopadmin.utils.EnvironmentUtil;
import com.zaxxer.hikari.HikariConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableJpaRepositories(
        basePackages = {"com.shop.shopadmin.repository"},
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)
// Mybatis Configure
@MapperScan(
        basePackages="com.shop.shopadmin.mapper", sqlSessionFactoryRef="mysqlSessionFactory"
)
public class DBConfig implements InitializingBean, DisposableBean {
    private final EnvironmentUtil envUtil;
    @Value("${database.entity}")
    private String[] entityScan;
    @Value("${database.mybatis.config-location}")
    private String mybatisConfigLocation;
    @Value("${database.mybatis.mapper-locations}")
    private String mybatisMapperLocations;


    @Bean
    @ConfigurationProperties(prefix="database.datasource")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Primary
    @Bean
    public DataSource dataSource() {
        return DBConfigHelper.getDataSource(hikariConfig());
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        return DBConfigHelper.getEntityManagerFactory(Constants.unitName, envUtil.getEnv(), dataSource(), entityScan);
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        return DBConfigHelper.getTransactionManager(entityManagerFactory().getObject());
    }

    //+----------------------------------------------------------------+
    //|                     Mybatis Configure                          |
    //+----------------------------------------------------------------+
    @Bean
    public SqlSessionFactory mysqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage(entityScan[0]);
        sqlSessionFactoryBean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(mybatisConfigLocation));
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mybatisMapperLocations));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate mysqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(mysqlSessionFactory());
    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
