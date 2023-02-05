package com.nasr.core.command;

import com.nasr.core.model.OrderDetailData;
import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReserveProductCommand  {


    @TargetAggregateIdentifier
    private  String productId;

    private  String orderId;

    private BigDecimal totalAmount;

    private  String customerId;

    private  Integer quantity;

    @Builder
    public ReserveProductCommand(String orderId, String customerId,BigDecimal totalAmount , OrderDetailData orderDetailData) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.totalAmount=totalAmount;
        this.productId=orderDetailData.getProductId();
        this.quantity=orderDetailData.getOrderQuantity();
    }
}
