package com.nasr.orderservice.query.api.data.embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailKey implements Serializable {

    public static final String ORDER_ID="order_id";
    public static final String PRODUCT_ID="product_id";

    @Column(name = ORDER_ID,nullable = false)
    private String orderId;

    @Column(name = PRODUCT_ID,nullable = false)
    private String productId;
}
