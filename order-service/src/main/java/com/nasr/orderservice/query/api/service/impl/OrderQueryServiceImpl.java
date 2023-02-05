package com.nasr.orderservice.query.api.service.impl;

import com.nasr.orderservice.query.api.data.Order;
import com.nasr.orderservice.query.api.data.OrderDetail;
import com.nasr.orderservice.query.api.repository.OrderQueryRepository;
import com.nasr.orderservice.query.api.service.OrderDetailQueryService;
import com.nasr.orderservice.query.api.service.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OrderQueryServiceImpl implements OrderQueryService {

    @Autowired
    private OrderQueryRepository repository;

    @Autowired
    private OrderDetailQueryService orderDetailQueryService;

    @Override
    @Transactional
    public void  saveOrder(Order order, OrderDetail orderDetail) {

        repository.save(order);
        orderDetailQueryService.save(orderDetail);
    }
}
