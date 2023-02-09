package com.nasr.core.event;

import com.nasr.core.base.event.BaseEvent;
import com.nasr.core.enumeration.PaymentStatus;
import com.nasr.core.model.OrderDetailData;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

import static com.nasr.core.enumeration.PaymentStatus.*;
import static com.nasr.core.model.UserPaymentDetail.PaymentDetail;

@Getter
public class PaymentProcessedEvent extends BaseEvent<String> {

    private final String orderId;
    private final BigDecimal totalAmount;
    private final PaymentStatus paymentStatus;
    private final OrderDetailData orderDetailData;
    private final PaymentDetail paymentDetail;

    @Builder
    public PaymentProcessedEvent(String id, String orderId,BigDecimal totalAmount,OrderDetailData orderDetailData, PaymentDetail paymentDetail) {
        super(id);
        this.orderId=orderId;
        this.totalAmount=totalAmount;
        this.paymentStatus= SUCCESS;
        this.orderDetailData=orderDetailData;
        this.paymentDetail=paymentDetail;
    }
}
