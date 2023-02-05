package com.nasr.orderservice.query.api.service.impl;

import com.nasr.orderservice.query.api.data.OrderDetail;
import com.nasr.orderservice.query.api.repository.OrderDetailQueryRepository;
import com.nasr.orderservice.query.api.service.OrderDetailQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OrderDetailQueryServiceImpl implements OrderDetailQueryService {

    @Autowired
    private OrderDetailQueryRepository repository;

    @Override
    @Transactional
    public void save(OrderDetail orderDetail) {
        repository.save(orderDetail);
    }
}
