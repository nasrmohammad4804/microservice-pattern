package com.nasr.core.command;

import com.nasr.core.base.command.BaseCommand;
import com.nasr.core.model.OrderDetailData;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

import static com.nasr.core.model.UserPaymentDetail.PaymentDetail;

@Getter
public class ProcessPaymentCommand {

    private final String orderId;
    private final BigDecimal totalAmount;
    private final OrderDetailData orderDetailData;
    private final PaymentDetail paymentDetail;

    @Builder
    public ProcessPaymentCommand(String orderId, BigDecimal totalAmount, OrderDetailData orderDetailData, PaymentDetail paymentDetail) {
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        this.orderDetailData = orderDetailData;
        this.paymentDetail = paymentDetail;
    }
}
