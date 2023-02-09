package com.nasr.core.command;

import com.nasr.core.base.command.BaseCommand;
import com.nasr.core.model.OrderDetailData;
import com.nasr.core.model.UserPaymentDetail;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

import static com.nasr.core.model.UserPaymentDetail.*;

@Getter
public class CancelPaymentProcessCommand  extends BaseCommand<String> {

    private final String orderId;
    private final BigDecimal totalAmount;

    private final PaymentDetail paymentDetail;
    private final OrderDetailData orderDetailData;
    private final String reason;

    @Builder
    public CancelPaymentProcessCommand(String paymentId, String orderId,BigDecimal totalAmount, PaymentDetail paymentDetail,OrderDetailData orderDetailData ,String reason){
        super(paymentId);
        this.orderId=orderId;
        this.totalAmount=totalAmount;
        this.paymentDetail=paymentDetail;
        this.orderDetailData=orderDetailData;
        this.reason=reason;
    }
}
