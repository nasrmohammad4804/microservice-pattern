package com.nasr.orderservice.query.api.service;

import com.nasr.orderservice.core.enumeration.OrderStatus;
import com.nasr.orderservice.query.api.data.Order;
import com.nasr.orderservice.query.api.data.OrderDetail;
import com.nasr.orderservice.query.api.dto.OrderDto;

public interface OrderQueryService {

    void  saveOrder(Order order, OrderDetail orderDetail);

    void updateOrderStatus(String orderId, OrderStatus orderStatus);

    OrderDto getOrderById(String orderId);
}
