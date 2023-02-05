package com.nasr.orderservice.command.api.dto;

import com.nasr.core.model.OrderDetailData;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PlaceOrderRequestDto {

    @NotNull(message = "orderTotalAmount is required !")
    private BigDecimal totalAmount;

    @NotBlank(message = "orderCustomerId is required !")
    private String customerId;

    @NotBlank(message = "orderAddress is required !")
    private String address;

    @NotNull(message = "orderDetail is required !")
    private OrderDetailData orderDetailData;
}
