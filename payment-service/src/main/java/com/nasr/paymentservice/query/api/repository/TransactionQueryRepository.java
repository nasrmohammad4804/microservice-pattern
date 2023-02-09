package com.nasr.paymentservice.query.api.repository;

import com.nasr.paymentservice.query.api.data.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionQueryRepository extends JpaRepository<Transaction,String> {
}
