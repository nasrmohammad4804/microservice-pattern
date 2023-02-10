package com.nasr.orderservice.query.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class OrderDto {

    private String orderId;
    private BigDecimal totalPrice;
    private String customerId;
}
