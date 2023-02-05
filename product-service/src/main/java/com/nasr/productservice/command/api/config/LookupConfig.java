package com.nasr.productservice.command.api.config;

import com.nasr.productservice.command.api.config.annotation.Lookup;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.nasr.productservice.command.api.repository",
        entityManagerFactoryRef = "lookupEntityManagerFactory",
        transactionManagerRef= "lookupTransactionManager",
        excludeFilters = @ComponentScan.Filter(Lookup.class)
)
public class LookupConfig {


    @Primary
    @Bean("lookup")
    @ConfigurationProperties("spring.datasource.hikari.lookup")
    public DataSource datasource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "lookupEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean projectionMasterEntityManager(EntityManagerFactoryBuilder builder) {
        return  builder.dataSource(datasource())
                .persistenceUnit("lookup")
                .properties(jpaProperties())
                .packages( "com.nasr.productservice.command.api.data",
                        "org.axonframework.eventhandling.tokenstore",
                        "org.axonframework.modelling.saga.repository.jpa",
                        "org.axonframework.eventsourcing.eventstore.jpa")
                .build();
    }

    @Bean(name = "lookupTransactionManager")
    public PlatformTransactionManager customerTransactionManager(
             EntityManagerFactory entityManagerFactory ) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        props.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.show_sql", "true");
        return props;
    }

}