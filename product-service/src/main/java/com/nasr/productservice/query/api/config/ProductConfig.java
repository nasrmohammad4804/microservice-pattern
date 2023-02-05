
package com.nasr.productservice.query.api.config;

import com.nasr.productservice.command.api.config.annotation.Lookup;
import com.nasr.productservice.query.api.config.annotation.Product;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
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
@EnableJpaRepositories(basePackages = "com.nasr.productservice.query.api.repository",
        entityManagerFactoryRef = "productEntityManagerFactory",
        transactionManagerRef= "productTransactionManager",
        excludeFilters = @ComponentScan.Filter(Product.class)
)
public class ProductConfig {



        @Bean("productDatasource")
        @ConfigurationProperties("spring.datasource.hikari.product")
        public DataSource datasource() {
                return DataSourceBuilder.create().type(HikariDataSource.class).build();
        }

        @Bean(name = "productEntityManagerFactory")
        public LocalContainerEntityManagerFactoryBean projectionMasterEntityManager(EntityManagerFactoryBuilder builder) {
                return  builder.dataSource(datasource())
                        .persistenceUnit("product")
                        .properties(jpaProperties())
                        .packages( "com.nasr.productservice.query.api.data",
                                "org.axonframework.eventhandling.tokenstore",
                                "org.axonframework.modelling.saga.repository.jpa",
                                "org.axonframework.eventsourcing.eventstore.jpa")
                        .build();
        }

        @Bean(name = "productTransactionManager")
        public PlatformTransactionManager customerTransactionManager(
                @Qualifier("productEntityManagerFactory") EntityManagerFactory entityManagerFactory ) {
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