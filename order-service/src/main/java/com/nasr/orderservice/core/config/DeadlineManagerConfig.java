package com.nasr.orderservice.core.config;

import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.config.ConfigurationScopeAwareProvider;
import org.axonframework.deadline.DeadlineManager;
import org.axonframework.deadline.SimpleDeadlineManager;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeadlineManagerConfig {


    @Bean
    public DeadlineManager deadlineManager(TransactionManager transactionManager, AxonConfiguration configuration){
        return SimpleDeadlineManager.builder()
                .transactionManager(transactionManager)
                .scopeAwareProvider(new ConfigurationScopeAwareProvider(configuration))
                .build();
    }
}
