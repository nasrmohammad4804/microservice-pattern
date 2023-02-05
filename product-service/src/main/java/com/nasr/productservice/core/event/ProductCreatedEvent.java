package com.nasr.productservice.core.event;

import com.nasr.core.base.event.BaseEvent;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;


@Getter
public class ProductCreatedEvent extends BaseEvent<String> {

    private final String name;
    private final BigDecimal price;
    private final Integer quantity;

    @Builder
    public ProductCreatedEvent(String id, String name, BigDecimal price, Integer quantity) {
        super(id);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
