package com.nasr.orderservice.query.api.repository;

import com.nasr.orderservice.query.api.data.Order;
import com.nasr.orderservice.query.api.dto.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderQueryRepository extends JpaRepository<Order,String> {

    @Query("select new com.nasr.orderservice.query.api.dto.OrderDto(o.id,o.totalPrice,o.customerId) from Order  as o where o.id=:orderId")
    Optional<OrderDto> findOrderById(String orderId);
}
