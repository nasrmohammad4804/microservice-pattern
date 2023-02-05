package com.nasr.orderservice.query.api.repository;

import com.nasr.orderservice.query.api.data.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailQueryRepository extends JpaRepository<OrderDetail,String> {
}
