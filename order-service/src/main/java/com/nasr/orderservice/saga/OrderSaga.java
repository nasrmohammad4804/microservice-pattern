package com.nasr.orderservice.saga;

import com.nasr.core.command.ReserveProductCommand;
import com.nasr.core.event.ProductReservedEvent;
import com.nasr.core.model.UserPaymentDetailResponseDto;
import com.nasr.core.query.GetUserPaymentDetailQuery;
import com.nasr.orderservice.core.event.OrderApprovedEvent;
import com.nasr.orderservice.core.event.OrderCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class OrderSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private transient QueryGateway queryGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent event) {

        log.info("orderCreateEvent received from order service with orderId: {}", event.getOrderId());

        ReserveProductCommand command = new ReserveProductCommand(
                event.getOrderId(), event.getCustomerId(), event.getTotalAmount(), event.getOrderDetailData()
        );

        CommandCallback<ReserveProductCommand, Object> commandCallback = (commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                commandResultMessage.exceptionResult().printStackTrace();
                //todo if command processing failed in reservedProductCommand use compensation transaction
                //need to raise rejectOrderCommand
            }

        };


        commandGateway.send(command, commandCallback);
    }

    //we don't need to change anything from userService
    //we  need to get user payment detail
    // two option that we can fetch this detail
    //1 . use rest api with GET method
    //2. use query to userService and get Detail and after that continue other step
    //note - better is when using event sourcing or cqrs fetch data with query instead of http GET request

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ProductReservedEvent event) {
        log.info("productReservedEvent received from product service with id :  {}", event.getProductId());

        GetUserPaymentDetailQuery query = new GetUserPaymentDetailQuery(event.getCustomerId());

        try {

            UserPaymentDetailResponseDto userPaymentDetail = queryGateway.query(query, UserPaymentDetailResponseDto.class)
                    .join();

            log.info("userPaymentDetail successfully fetched from user-service for userId: {}  with name: {}",
                    event.getCustomerId(), userPaymentDetail.getFirstName().concat(userPaymentDetail.getLastName()));

        } catch (Exception e) {
            log.error("getUserPaymentDetailQuery process was not successful !");
            //compensating transaction
        }


    }


    @EndSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderApprovedEvent event) {
        log.info("orderApprovedEvent received from order service with orderId: {}", event.getOrderId());

    }

}
