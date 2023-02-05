package com.nasr.productservice.command.api.interceptor;

import com.nasr.productservice.command.api.command.CreateProductCommand;
import com.nasr.productservice.command.api.service.ProductLookupService;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiFunction;

@Component
@Slf4j
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    @Autowired
    private ProductLookupService productLookupService;
/*
* we can validate command in command dispatch interceptor or we can handle when
* command consumed by command handler from command bus in command handler
* i prefer to use command dispatch interceptor to get message before message
* published to command bus
* note if processing fail in interceptor then event dont published to command bus and then command handler don't consume any command */
    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> list) {
        return (index , command) -> {

            log.info("intercepted command  : {} is received",command.getPayload());

            if (!command.getPayloadType().equals(CreateProductCommand.class))
                return command;

            CreateProductCommand createProductCommand =(CreateProductCommand) command.getPayload();

            productLookupService.checkExistsByName(createProductCommand.getName());
            return command;

        };
    }
}
