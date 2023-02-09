package com.nasr.orderservice.core.event;

import com.nasr.core.base.event.BaseEvent;
import com.nasr.orderservice.core.enumeration.OrderStatus;
import lombok.Getter;

import static com.nasr.orderservice.core.enumeration.OrderStatus.*;

@Getter
public class OrderRejectedEvent extends BaseEvent<String> {

    private final OrderStatus status;

    private final String reason;

    public OrderRejectedEvent(String id,String reason) {
        super(id);
        this.reason=reason;
        this.status = REJECTED;
    }
}
