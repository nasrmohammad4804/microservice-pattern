package com.nasr.orderservice.query.api.service;

import com.nasr.orderservice.query.api.data.Order;
import com.nasr.orderservice.query.api.data.OrderDetail;

public interface OrderQueryService {

    void  saveOrder(Order order, OrderDetail orderDetail);
}
