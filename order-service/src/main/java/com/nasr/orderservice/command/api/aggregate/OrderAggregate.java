package com.nasr.orderservice.command.api.aggregate;

import com.nasr.core.base.aggregate.BaseAggregate;
import com.nasr.core.model.OrderDetailData;
import com.nasr.orderservice.command.api.commands.CreateOrderCommand;
import com.nasr.orderservice.core.command.ApproveOrderCommand;
import com.nasr.orderservice.core.command.RejectOrderCommand;
import com.nasr.orderservice.core.enumeration.OrderStatus;
import com.nasr.orderservice.core.event.OrderApprovedEvent;
import com.nasr.orderservice.core.event.OrderCreatedEvent;
import com.nasr.orderservice.core.event.OrderRejectedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.nasr.orderservice.core.enumeration.OrderStatus.CREATED;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Setter
@Getter
@NoArgsConstructor
public class OrderAggregate extends BaseAggregate<String> {

    private BigDecimal totalAmount;
    private LocalDateTime orderDate;
    private String customerId;
    private OrderStatus orderStatus;
    private String address;
    private OrderDetailData orderDetailData;

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {

        OrderCreatedEvent event = new OrderCreatedEvent.OrderCreatedEventBuilder(command.getId())
                .customerId(command.getCustomerId())
                .totalAmount(command.getTotalAmount())
                .orderDate(command.getOrderDate())
                .address(command.getAddress())
                .orderDetailData(command.getOrderDetailsData())
                .orderStatus(CREATED)
                .build();

        apply(event);
    }

    @CommandHandler
    public void handle(ApproveOrderCommand command){
        //we don't need to validate anything
        apply(new OrderApprovedEvent(command.getId()));
    }

    @CommandHandler
    public void handle(RejectOrderCommand command){
        OrderRejectedEvent event = new OrderRejectedEvent(command.getId(), command.getReason());

        apply(event);
    }


    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.id = event.getId();
        this.orderDate = event.getOrderDate();
        this.customerId = event.getCustomerId();
        this.totalAmount = event.getTotalAmount();
        this.orderDetailData=event.getOrderDetailData();
        this.orderStatus = event.getOrderStatus();
    }

    @EventSourcingHandler
    public void on(OrderApprovedEvent event){
        this.orderStatus=event.getOrderStatus();
    }

    @EventSourcingHandler
    public void on(OrderRejectedEvent event){
        this.orderStatus=event.getStatus();
    }
}
