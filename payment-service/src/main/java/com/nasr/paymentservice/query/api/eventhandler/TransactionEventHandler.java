package com.nasr.paymentservice.query.api.eventhandler;

import com.nasr.core.event.PaymentProcessCancelledEvent;
import com.nasr.core.event.PaymentProcessedEvent;
import com.nasr.paymentservice.query.api.data.Transaction;
import com.nasr.paymentservice.query.api.data.embeddable.PaymentDetail;
import com.nasr.paymentservice.query.api.service.TransactionQueryService;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.nasr.paymentservice.core.constant.ConstantField.TRANSACTION_GROUP;
import static com.nasr.paymentservice.query.api.util.PaymentUtility.*;
import static com.nasr.paymentservice.core.enumeration.PaymentType.*;
import static java.time.LocalDateTime.now;

@Component
@ProcessingGroup(TRANSACTION_GROUP)
public class TransactionEventHandler {

    @Autowired
    private TransactionQueryService transactionQueryService;

    @EventHandler
    public void handle(PaymentProcessedEvent event) {

        //we at first decrease amount from this account after that save transaction in db
        pay(event.getPaymentDetail(), WITHDRAW);

        PaymentDetail paymentDetail = new PaymentDetail();

        BeanUtils.copyProperties(event.getPaymentDetail(), paymentDetail);

        Transaction transaction = Transaction.builder()
                .id(event.getId())
                .orderId(event.getOrderId())
                .paymentTime(now())
                .status(event.getPaymentStatus())
                .paymentDetail(paymentDetail)
                .build();


        transactionQueryService.save(transaction);
    }

    @EventHandler
    public void handle(PaymentProcessCancelledEvent event){
        /* at first we revert money to user account  which we have already taken from it
        * after that change transaction paymentStatus to Failed */
        pay(event.getPaymentDetail(),DEPOSIT);

        transactionQueryService.updatePaymentStatus(event.getId(),event.getPaymentStatus());
    }
}
