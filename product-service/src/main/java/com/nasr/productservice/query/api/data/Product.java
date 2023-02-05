package com.nasr.productservice.query.api.data;

import com.nasr.core.base.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import static com.nasr.productservice.query.api.data.Product.NAME;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {NAME})
})

public class Product extends BaseEntity<String> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1;

    public static final String NAME="name";

    @Column(name = NAME)
    private String name;

    private BigDecimal price;

    private Integer quantity;


    @Builder
    public Product(String id, String name, BigDecimal price, Integer quantity) {
        super(id);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
