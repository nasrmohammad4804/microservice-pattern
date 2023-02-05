package com.nasr.orderservice.query.api.data;

import com.nasr.core.base.domain.BaseEntity;
import com.nasr.orderservice.core.enumeration.OrderStatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.nasr.orderservice.query.api.data.Order.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME )
@Setter
@Getter
@NoArgsConstructor

public class Order extends BaseEntity<String> {

    public static final String TABLE_NAME="order_table";

    private BigDecimal totalPrice;
    private String customerId;
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Builder
    public Order(String id, BigDecimal totalPrice, String customerId, LocalDateTime orderDate, OrderStatus orderStatus) {
        super(id);
        this.totalPrice = totalPrice;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }
}
