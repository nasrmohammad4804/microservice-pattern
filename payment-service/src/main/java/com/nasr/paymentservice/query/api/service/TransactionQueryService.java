package com.nasr.paymentservice.query.api.service;

import com.nasr.core.enumeration.PaymentStatus;
import com.nasr.paymentservice.query.api.data.Transaction;

public interface TransactionQueryService {
    void save(Transaction transaction);

    void updatePaymentStatus(String id, PaymentStatus paymentStatus);
}
