package com.nasr.paymentservice.command.api.aggregate;

import com.nasr.core.base.aggregate.BaseAggregate;
import com.nasr.core.command.CancelPaymentProcessCommand;
import com.nasr.core.command.ProcessPaymentCommand;
import com.nasr.core.enumeration.PaymentStatus;
import com.nasr.core.event.PaymentProcessCancelledEvent;
import com.nasr.core.event.PaymentProcessedEvent;
import com.nasr.core.model.Order;
import com.nasr.core.model.UserPaymentDetail;
import com.nasr.core.query.GetOrderDetailQuery;
import com.nasr.core.query.GetUserPaymentDetailQuery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static com.nasr.core.model.UserPaymentDetail.*;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
@Getter
public class TransactionAggregate extends BaseAggregate<String> {

    private String orderId;
    private PaymentStatus paymentStatus;
    private PaymentDetail paymentDetail;


    @CommandHandler
    public TransactionAggregate(ProcessPaymentCommand command, QueryGateway queryGateway) {

        Order order = queryGateway.query(new GetOrderDetailQuery(command.getOrderId()), Order.class)
                .join();

        UserPaymentDetail userPaymentDetail = queryGateway.query(new GetUserPaymentDetailQuery(order.getUserId()), UserPaymentDetail.class)
                .join();

        //validation check is valid card or have sufficient balance


        PaymentProcessedEvent event = PaymentProcessedEvent.builder()
                .id(command.getId())
                .orderId(command.getOrderId())
                .paymentDetail(userPaymentDetail.getPaymentDetail())
                .orderDetailData(order.getOrderDetail())
                .build();

        apply(event);
    }

    @CommandHandler
    public void handle(CancelPaymentProcessCommand command) {
        //don't need to validate anything

        PaymentProcessCancelledEvent event = PaymentProcessCancelledEvent
                .builder()
                .paymentId(command.getId())
                .orderId(command.getOrderId())
                .orderDetailData(command.getOrderDetailData())
                .paymentDetail(command.getPaymentDetail())
                .reason(command.getReason())
                .build();

        apply(event);
    }

    @EventSourcingHandler
    public void on(PaymentProcessedEvent event) {
        this.id = event.getId();
        this.orderId = event.getOrderId();
        this.paymentStatus = event.getPaymentStatus();
        this.paymentDetail = event.getPaymentDetail();
    }

    @EventSourcingHandler
    public void on(PaymentProcessCancelledEvent event){
        this.paymentStatus=event.getPaymentStatus();
    }
}
