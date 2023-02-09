package com.nasr.core.event;

import com.nasr.core.base.event.BaseEvent;
import com.nasr.core.model.OrderDetailData;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;


@Getter
public class ProductReservedEvent extends BaseEvent<String> implements Serializable {

    public static  final long serialVersionUID=1L;

    private final String orderId;
    private final String customerId;
    private final Integer quantity;
    private final BigDecimal totalAmount;

    @Builder
    public ProductReservedEvent(String orderId, String customerId,BigDecimal totalAmount, OrderDetailData orderDetailData) {
        super(orderDetailData.getProductId());
        this.orderId = orderId;
        this.customerId = customerId;
        this.totalAmount=totalAmount;
        this.quantity=orderDetailData.getOrderQuantity();
    }

}
