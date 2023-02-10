package com.nasr.core.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Order {

    private final String orderId;
    private final BigDecimal totalAmount;
    private final String userId;

    private final OrderDetailData orderDetail;
}
