package com.nasr.productservice.query.api.dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class ProductResponseDto{

    private final String id;
    private final String name;
    private final BigDecimal price;
    private final Integer quantity;

}
