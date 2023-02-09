package com.nasr.core.event;

import com.nasr.core.base.event.BaseEvent;
import com.nasr.core.enumeration.PaymentStatus;
import com.nasr.core.model.OrderDetailData;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

import static com.nasr.core.enumeration.PaymentStatus.FAILED;
import static com.nasr.core.model.UserPaymentDetail.PaymentDetail;

@Getter
public class PaymentProcessCancelledEvent extends BaseEvent<String> {

    private final String orderId;
    private final BigDecimal totalAmount;
    private final PaymentStatus paymentStatus;
    private final OrderDetailData orderDetailData;
    private final PaymentDetail paymentDetail;
    private final String reason;

    @Builder
    public PaymentProcessCancelledEvent(String paymentId, String orderId,BigDecimal totalAmount,OrderDetailData orderDetailData,PaymentDetail paymentDetail, String reason) {
        super(paymentId);
        this.orderId = orderId;
        this.totalAmount=totalAmount;
        this.paymentStatus = FAILED;
        this.orderDetailData=orderDetailData;
        this.paymentDetail=paymentDetail;
        this.reason = reason;
    }
}
