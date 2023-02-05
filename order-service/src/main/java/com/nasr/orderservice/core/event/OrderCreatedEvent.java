package com.nasr.orderservice.core.event;

import com.nasr.core.base.event.BaseEvent;

import com.nasr.core.model.OrderDetailData;
import com.nasr.orderservice.core.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent  {

    private String orderId;
    private BigDecimal totalAmount;
    private LocalDateTime orderDate;
    private String customerId;
    private String address;
    private OrderStatus orderStatus;
    private OrderDetailData orderDetailData;

    public OrderCreatedEvent(OrderCreatedEventBuilder builder) {

        this.orderId=builder.orderId;
        this.customerId=builder.customerId;
        this.orderDate=builder.orderDate;
        this.totalAmount=builder.totalAmount;
        this.address=builder.address;
        this.orderDetailData=builder.orderDetailData;
    }

    @Getter
    public static class OrderCreatedEventBuilder{

        private String orderId;
        private BigDecimal totalAmount;
        private LocalDateTime orderDate;
        private String customerId;
        private String address;
        private OrderStatus orderStatus;
        private OrderDetailData orderDetailData;

        public  OrderCreatedEventBuilder(String orderId){
            this.orderId=orderId;

        }
        public OrderCreatedEventBuilder orderStatus(OrderStatus orderStatus){
            this.orderStatus=orderStatus;
            return this;
        }
        public OrderCreatedEventBuilder totalAmount(BigDecimal totalAmount){
            this.totalAmount=totalAmount;
            return this;
        }
        public OrderCreatedEventBuilder orderDate(LocalDateTime orderDate){
            this.orderDate=orderDate;
            return this;
        }
        public OrderCreatedEventBuilder customerId(String customerId){
            this.customerId=customerId;
            return this;
        }
        public OrderCreatedEventBuilder orderDetailData(OrderDetailData data){
            this.orderDetailData=data;
            return this;
        }
        public OrderCreatedEventBuilder address(String  address){
            this.address=address;
            return this;
        }
        public OrderCreatedEvent build(){
            return new OrderCreatedEvent(this);
        }

    }
}
