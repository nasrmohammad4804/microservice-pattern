package com.nasr.orderservice.query.api.repository;

import com.nasr.orderservice.query.api.data.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderDetailQueryRepository extends JpaRepository<OrderDetail,String> {

    @Query("select o from OrderDetail  as o where o.id.orderId=:orderId")
    Optional<OrderDetail> findByOrderId(String orderId);
}
