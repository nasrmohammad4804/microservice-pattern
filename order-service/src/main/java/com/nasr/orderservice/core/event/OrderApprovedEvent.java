package com.nasr.orderservice.core.event;

import com.nasr.core.base.event.BaseEvent;
import com.nasr.orderservice.core.enumeration.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderApprovedEvent  {

    private String orderId;
    private final OrderStatus orderStatus;

    public OrderApprovedEvent(String orderId, OrderStatus orderStatus) {
        this.orderId=orderId;
        this.orderStatus = orderStatus;
    }
}
