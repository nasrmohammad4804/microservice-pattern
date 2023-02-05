package com.nasr.orderservice.command.api.aggregate;

import com.nasr.core.base.aggregate.BaseAggregate;
import com.nasr.core.model.OrderDetailData;
import com.nasr.orderservice.command.api.commands.CreateOrderCommand;
import com.nasr.orderservice.core.enumeration.OrderStatus;
import com.nasr.orderservice.core.event.OrderCreatedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.nasr.orderservice.core.enumeration.OrderStatus.CREATED;

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

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.id = event.getOrderId();
        this.orderDate = event.getOrderDate();
        this.customerId = event.getCustomerId();
        this.totalAmount = event.getTotalAmount();
        this.orderDetailData=event.getOrderDetailData();
        this.orderStatus = event.getOrderStatus();
    }
}
