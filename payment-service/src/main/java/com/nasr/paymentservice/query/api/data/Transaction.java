package com.nasr.paymentservice.query.api.data;

import com.nasr.core.base.domain.BaseEntity;
import com.nasr.core.enumeration.PaymentStatus;
import com.nasr.paymentservice.query.api.data.embeddable.PaymentDetail;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends BaseEntity<String> {

    public static final String ORDER_ID = "order_id";
    public static final String PAYMENT_TIME = "payment_time";

    @Column(name = ORDER_ID)
    private String orderId;

    @Column(name = PAYMENT_TIME)
    private LocalDateTime paymentTime;


    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Embedded
    private PaymentDetail paymentDetail;

    @Builder
    public Transaction(String id, String orderId, LocalDateTime paymentTime,PaymentStatus status, PaymentDetail paymentDetail) {
        super(id);
        this.orderId = orderId;
        this.paymentTime = paymentTime;
        this.paymentStatus=status;
        this.paymentDetail = paymentDetail;
    }
}
