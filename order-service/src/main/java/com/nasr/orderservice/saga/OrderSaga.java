package com.nasr.orderservice.saga;

import com.nasr.core.base.event.BaseEvent;
import com.nasr.core.command.CancelPaymentProcessCommand;
import com.nasr.core.command.CancelProductReservationCommand;
import com.nasr.core.command.ReserveProductCommand;
import com.nasr.core.event.PaymentProcessCancelledEvent;
import com.nasr.core.event.PaymentProcessedEvent;
import com.nasr.core.event.ProductReservationCancelledEvent;
import com.nasr.core.event.ProductReservedEvent;
import com.nasr.orderservice.core.command.ApproveOrderCommand;
import com.nasr.orderservice.core.command.RejectOrderCommand;
import com.nasr.orderservice.core.event.OrderApprovedEvent;
import com.nasr.orderservice.core.event.OrderCreatedEvent;
import com.nasr.orderservice.core.event.OrderRejectedEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.deadline.DeadlineManager;
import org.axonframework.deadline.annotation.DeadlineHandler;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;

import static com.nasr.orderservice.core.constant.ConstantField.PAYMENT_PROCESSING_DEADLINE;
import static com.nasr.orderservice.saga.util.DeadlineDataUtility.paymentProcessingDeadline;

/*
 * saga instance start with orderCreatedEvent and maybe between process fail
 * then when saga ends with orderApprovedEvent that we know all step is successful
 * but if we arrive to orderRejectEvent we roll back all changes with reversOrder and
 * we can say transaction rollback confidently
 * */

@Saga
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class OrderSaga {

    @Autowired
    private transient CommandGateway commandGateway;

/*    @Autowired
    private transient QueryGateway queryGateway;*/

    @Autowired
    private transient DeadlineManager deadlineManager;

    @StartSaga
    @SagaEventHandler(associationProperty = "id", keyName = "orderId")
    public void handle(OrderCreatedEvent event) {

        log.info("orderCreateEvent received from order service with orderId: {}", event.getId());

        ReserveProductCommand command = new ReserveProductCommand(
                event.getId(), event.getCustomerId(), event.getTotalAmount(), event.getOrderDetailData()
        );

        CommandCallback<ReserveProductCommand, Object> commandCallback = (commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                log.error("productReservationCommand was not successful and we need to start RejectOrderCommand compensating transaction ");
                rejectOrder(event, commandResultMessage.exceptionResult().getMessage());
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
        log.info("productReservedEvent received from product service with id :  {}", event.getId());

        schedulePaymentProcessingDeadline(event);
    }


    @SagaEventHandler(associationProperty = "orderId")
    public void handle(PaymentProcessedEvent event) {
        log.info("paymentProcessedEvent received from payment-service with paymentId : {}", event.getId());

        String deadlineId = paymentProcessingDeadline.remove(event.getOrderId());
        deadlineManager.cancelSchedule(PAYMENT_PROCESSING_DEADLINE, deadlineId);

        ApproveOrderCommand command = new ApproveOrderCommand(event.getOrderId());

        CommandCallback<ApproveOrderCommand, ?> callback = (commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                log.error("dont able to approve order with id : " + event.getOrderId());
                /* start compensating transaction for payment service and deposit money to user account with paymentDetails */
                cancelPaymentProcess(event, commandResultMessage.exceptionResult().getMessage());

            }
        };
        commandGateway.send(command, callback);
    }


    @EndSaga
    @SagaEventHandler(associationProperty = "id", keyName = "orderId")
    public void handle(OrderApprovedEvent event) {
        log.info("order is approved . Order Saga is completed for orderId : " + event.getId());
    }


    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ProductReservationCancelledEvent event) {
        log.info("product Reservation Cancelled with product id : {}   and orderId: {}", event.getId(), event.getOrderId());
        rejectOrder(new BaseEvent<>(event.getOrderId()), event.getReason());
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(PaymentProcessCancelledEvent event) {
        log.info("payment process reverted for paymentId : {}", event.getId());
        cancelProductReservation(
                new ProductReservedEvent(event.getOrderId(), null, null, event.getOrderDetailData()), event.getReason()
        );
    }


    @EndSaga
    @SagaEventHandler(associationProperty = "id", keyName = "orderId")
    public void handle(OrderRejectedEvent event) {
        log.info("order is rejected . Order Saga completed for orderId : " + event.getId());
    }

    @DeadlineHandler(deadlineName = PAYMENT_PROCESSING_DEADLINE)
    public void handlePaymentProcessingDeadline(ProductReservedEvent event) {
        log.info("payment processing deadline took placed because order with id: {} dont payed", event.getOrderId());
        paymentProcessingDeadline.remove(event.getOrderId());

        String reason = String.format("dont pay for specific order : %s   after 1 hours ", event.getOrderId());
        cancelProductReservation(event, reason);
    }

    private void schedulePaymentProcessingDeadline(ProductReservedEvent event) {
        String deadlineId = deadlineManager.schedule(Duration.ofHours(1), PAYMENT_PROCESSING_DEADLINE, event);
        paymentProcessingDeadline.put(event.getOrderId(), deadlineId);

    }

    private void cancelProductReservation(ProductReservedEvent event, String reason) {

        CancelProductReservationCommand command = CancelProductReservationCommand.builder()
                .orderId(event.getOrderId())
                .productId(event.getId())
                .quantity(event.getQuantity())
                .reason(reason)
                .build();

        commandGateway.send(command);
    }

    private void cancelPaymentProcess(PaymentProcessedEvent event, String message) {

        CancelPaymentProcessCommand command = CancelPaymentProcessCommand
                .builder()
                .paymentId(event.getId())
                .orderId(event.getOrderId())
                .totalAmount(event.getTotalAmount())
                .paymentDetail(event.getPaymentDetail())
                .orderDetailData(event.getOrderDetailData())
                .reason(message)
                .build();

        commandGateway.send(command);
    }

    private void rejectOrder(BaseEvent<String> event, String reason) {

        RejectOrderCommand command = new RejectOrderCommand(event.getId(), reason);

        commandGateway.send(command);
    }


}
