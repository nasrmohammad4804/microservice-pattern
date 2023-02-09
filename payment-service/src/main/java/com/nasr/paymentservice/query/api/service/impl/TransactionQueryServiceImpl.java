package com.nasr.paymentservice.query.api.service.impl;

import com.nasr.core.enumeration.PaymentStatus;
import com.nasr.paymentservice.query.api.data.Transaction;
import com.nasr.paymentservice.query.api.repository.TransactionQueryRepository;
import com.nasr.paymentservice.query.api.service.TransactionQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TransactionQueryServiceImpl implements TransactionQueryService {

    @Autowired
    private TransactionQueryRepository repository;

    @Override
    @Transactional
    public void save(Transaction transaction) {
        repository.save(transaction);
    }

    @Override
    @Transactional
    public void updatePaymentStatus(String id, PaymentStatus paymentStatus) {
        repository.findById(id)
                .ifPresentOrElse(transaction -> transaction.setPaymentStatus(paymentStatus),
                        () -> {
                            throw new IllegalStateException("dont find any transaction with id : " + id);
                        });
    }
}
