package com.nasr.productservice.core.exception;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Nonnull;


/*this event error handler takes error occurred in event handler
 * and need to re throw(as last level) to follow up and rollback entire transaction
 * */
public class EventErrorHandler implements ListenerInvocationErrorHandler {

    private final Logger  logger = LoggerFactory.getLogger(EventErrorHandler.class);

    @Override
    public void onError(@Nonnull Exception exception, @Nonnull EventMessage<?> event, @Nonnull EventMessageHandler eventHandler) throws Exception {
        logger.warn("exception : [{}] received from event handler: [{}}  ", exception.getMessage(),eventHandler.getTargetType().getName());
        throw exception;
    }
}
