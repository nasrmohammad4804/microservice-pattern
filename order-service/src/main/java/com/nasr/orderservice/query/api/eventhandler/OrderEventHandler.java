package com.nasr.orderservice.query.api.eventhandler;

import com.nasr.orderservice.core.event.OrderApprovedEvent;
import com.nasr.orderservice.core.event.OrderCreatedEvent;
import com.nasr.orderservice.core.event.OrderRejectedEvent;
import com.nasr.orderservice.query.api.data.Order;
import com.nasr.orderservice.query.api.data.OrderDetail;
import com.nasr.orderservice.query.api.data.embeddable.OrderDetailKey;
import com.nasr.orderservice.query.api.service.OrderQueryService;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.nasr.orderservice.core.constant.ConstantField.ORDER_GROUP;

@Component
@ProcessingGroup(ORDER_GROUP)
public class OrderEventHandler {

    @Autowired
    private OrderQueryService orderQueryService;


    @EventHandler
    public void handle(OrderCreatedEvent event){

        Order order= Order.builder()
                .id(event.getId())
                .orderDate(event.getOrderDate())
                .orderStatus(event.getOrderStatus())
                .totalPrice(event.getTotalAmount())
                .customerId(event.getCustomerId())
                .build();


        OrderDetail orderDetail=OrderDetail.builder()
                .id(new OrderDetailKey(event.getId(),event.getOrderDetailData().getProductId()))
                .orderQuantity(event.getOrderDetailData().getOrderQuantity())
                .build();

        orderQueryService.saveOrder(order,orderDetail);
    }


    @EventHandler
    public void handle(OrderApprovedEvent event){
        orderQueryService.updateOrderStatus(event.getId(),event.getOrderStatus());
    }

    @EventHandler
    public void handle(OrderRejectedEvent event){
        orderQueryService.updateOrderStatus(event.getId(),event.getStatus());
    }

}
