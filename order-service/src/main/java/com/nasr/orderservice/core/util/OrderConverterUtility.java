package com.nasr.orderservice.core.util;

import com.nasr.orderservice.command.api.commands.CreateOrderCommand;
import com.nasr.orderservice.command.api.dto.PlaceOrderRequestDto;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderConverterUtility {

    public static CreateOrderCommand convertPlaceOrderRequestToCommand(PlaceOrderRequestDto dto){
        return CreateOrderCommand.builder()
                .orderId(UUID.randomUUID().toString())
                .orderDate(LocalDateTime.now())
                .address(dto.getAddress())
                .customerId(dto.getCustomerId())
                .totalAmount(dto.getTotalAmount())
                .orderDetail(dto.getOrderDetailData())
                .build();
    }
}
