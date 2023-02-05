package com.nasr.orderservice;

import com.thoughtworks.xstream.XStream;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.PropagatingErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import static com.nasr.orderservice.core.constant.ConstantField.ORDER_GROUP;

@SpringBootApplication
@EnableEurekaClient
public class OrderServiceApplication {



	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Autowired
	public void configure(EventProcessingConfigurer config){
		config.registerListenerInvocationErrorHandler(
				ORDER_GROUP,configure -> PropagatingErrorHandler.instance()
		);
	}

}
