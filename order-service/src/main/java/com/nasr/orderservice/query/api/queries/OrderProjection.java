package com.nasr.orderservice.query.api.queries;

import com.nasr.core.model.Order;
import com.nasr.core.model.OrderDetailData;
import com.nasr.core.query.GetOrderDetailQuery;
import com.nasr.orderservice.query.api.data.OrderDetail;
import com.nasr.orderservice.query.api.dto.OrderDto;
import com.nasr.orderservice.query.api.service.OrderDetailQueryService;
import com.nasr.orderservice.query.api.service.OrderQueryService;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderProjection {

    @Autowired
    private OrderQueryService orderQueryService;

    @Autowired
    private OrderDetailQueryService orderDetailQueryService;

    @QueryHandler
    public Order handle(GetOrderDetailQuery query) {

        OrderDto orderDto = orderQueryService.getOrderById(query.getOrderId());
        OrderDetail orderDetail = orderDetailQueryService.getByOrderId(query.getOrderId());

        return Order.builder()
                .orderId(orderDto.getOrderId())
                .totalAmount(orderDto.getTotalPrice())
                .userId(orderDto.getCustomerId())
                .orderDetail(new OrderDetailData(orderDetail.getId().getProductId(), orderDetail.getOrderQuantity()))
                .build();
    }
}
