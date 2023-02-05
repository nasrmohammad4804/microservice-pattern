package com.nasr.orderservice.query.api.repository;

import com.nasr.orderservice.query.api.data.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderQueryRepository extends JpaRepository<Order,String> {
}
