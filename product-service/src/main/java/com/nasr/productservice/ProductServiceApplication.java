package com.nasr.productservice;

import com.nasr.productservice.command.api.interceptor.CreateProductCommandInterceptor;
import com.nasr.productservice.core.exception.EventErrorHandler;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import static com.nasr.productservice.core.constant.ConstantField.PRODUCT_PROCESSING_GROUP;

@SpringBootApplication
public class ProductServiceApplication {



	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Bean(name = "chainedTransactionManager")
	@Primary
	public ChainedTransactionManager transactionManager(
			@Qualifier("productTransactionManager")PlatformTransactionManager productTransactionManager,
			@Qualifier("lookupTransactionManager") PlatformTransactionManager productLookupTransactionManager){

		return new ChainedTransactionManager(productTransactionManager,productLookupTransactionManager);
	}


	/*need  register ListenerInvocationErrorHandler to event processing config
	*first argument take processing group name
	* and the second argument it takes default for example PropagationErrorHandler or custom implementation like EventErrorHandler
	* */

	@Autowired
	public void configure(EventProcessingConfigurer config){
		config.registerListenerInvocationErrorHandler
				(PRODUCT_PROCESSING_GROUP,configuration -> new EventErrorHandler());
	}

	@Autowired
	public void registerInterceptorToCommandBus(CommandBus commandBus, CreateProductCommandInterceptor interceptor){
		commandBus.registerDispatchInterceptor(interceptor);
	}

}
