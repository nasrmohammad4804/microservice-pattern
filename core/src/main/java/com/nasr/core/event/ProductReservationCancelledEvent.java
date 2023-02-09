package com.nasr.core.event;

import com.nasr.core.base.event.BaseEvent;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductReservationCancelledEvent extends BaseEvent<String> {


    private final String orderId;

    private final Integer quantity;

    private final String reason;

    @Builder
    public ProductReservationCancelledEvent(String id, String orderId, Integer quantity, String reason) {
        super(id);
        this.orderId = orderId;
        this.quantity = quantity;
        this.reason = reason;
    }
}
