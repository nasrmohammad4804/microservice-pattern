package com.nasr.productservice.command.api.aggregate;

import com.nasr.core.base.aggregate.BaseAggregate;
import com.nasr.core.command.CancelProductReservationCommand;
import com.nasr.core.command.ReserveProductCommand;
import com.nasr.core.event.ProductReservationCancelledEvent;
import com.nasr.core.event.ProductReservedEvent;
import com.nasr.core.model.OrderDetailData;
import com.nasr.productservice.command.api.command.CreateProductCommand;
import com.nasr.productservice.core.event.ProductCreatedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
@Getter
public class ProductAggregate extends BaseAggregate<String> {


    private String name;

    private BigDecimal price;

    private Integer quantity;


    /*note . we validate command in command handler
     * or we can validate that command from another place like service or validator
     * and call them from command handler */

    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {
//        command handler for validation command received from command bus
//        and if valid and need to state of aggregate changed raise event


        ProductCreatedEvent event = ProductCreatedEvent.builder()
                .id(command.getId())
                .name(command.getName())
                .price(command.getPrice())
                .quantity(command.getQuantity())
                .build();

        apply(event);

    }

    @CommandHandler
    public void handle(ReserveProductCommand command) {

        if (this.quantity < command.getQuantity())
            throw new IllegalStateException("dont sufficient product exists in stock");

        ProductReservedEvent event = ProductReservedEvent.builder()
                .customerId(command.getCustomerId())
                .orderDetailData(new OrderDetailData(command.getProductId(), command.getQuantity()))
                .totalAmount(command.getTotalAmount())
                .orderId(command.getOrderId())
                .build();

        apply(event);

    }

    @CommandHandler
    public void handle(CancelProductReservationCommand command) {

        ProductReservationCancelledEvent event = ProductReservationCancelledEvent
                .builder()
                .id(command.getProductId())
                .orderId(command.getOrderId())
                .quantity(command.getQuantity())
                .reason(command.getReason())
                .build();

        apply(event);
    }

    /* note when  apply event on command handler at first dispatch event to event handler for this aggregate
     * and after that scheduled for other event handler like (insert to read database)  */

    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.price = event.getPrice();
        this.quantity = event.getQuantity();
    }

    @EventSourcingHandler
    public void on(ProductReservedEvent event) {
        this.quantity -= event.getQuantity();
    }


    @EventSourcingHandler
    public void on(ProductReservationCancelledEvent event){
        this.quantity+=event.getQuantity();
    }


}
