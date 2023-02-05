package com.nasr.orderservice.query.api.data;

import com.nasr.core.base.domain.BaseEntity;
import com.nasr.orderservice.query.api.data.embeddable.OrderDetailKey;
import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetail {

    @EmbeddedId
    private OrderDetailKey id;

    private Integer orderQuantity;
}
