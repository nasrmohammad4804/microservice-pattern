package com.nasr.orderservice.command.api.commands;

import com.nasr.core.base.command.BaseCommand;
import com.nasr.core.model.OrderDetailData;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class CreateOrderCommand extends BaseCommand<String> {
    private BigDecimal totalAmount;
    private LocalDateTime orderDate;
    private String customerId;
    private OrderDetailData orderDetailsData;
    private String address;

    @Builder
    public CreateOrderCommand(String orderId, BigDecimal totalAmount, LocalDateTime orderDate,
                              String customerId,String address, OrderDetailData orderDetail) {

        super(orderId);
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.address=address;
        this.orderDetailsData = orderDetail;
    }

}
