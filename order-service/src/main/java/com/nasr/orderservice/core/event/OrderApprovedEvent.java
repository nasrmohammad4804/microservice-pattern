package com.nasr.orderservice.core.event;

import com.nasr.core.base.event.BaseEvent;
import com.nasr.orderservice.core.enumeration.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import static com.nasr.orderservice.core.enumeration.OrderStatus.*;

@Setter
@Getter
public class OrderApprovedEvent  extends BaseEvent<String>{

    private final OrderStatus orderStatus;

    public OrderApprovedEvent(String orderId) {
        super(orderId);
        this.orderStatus = APPROVED;
    }
}
