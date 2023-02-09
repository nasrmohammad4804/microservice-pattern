package com.nasr.paymentservice;

import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.PropagatingErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import static com.nasr.paymentservice.core.constant.ConstantField.TRANSACTION_GROUP;

@SpringBootApplication
@EnableEurekaClient
public class PaymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }

    @Autowired
    public void  configure(EventProcessingConfigurer config){

        config.registerListenerInvocationErrorHandler(TRANSACTION_GROUP,configuration -> PropagatingErrorHandler.instance());
    }
}
