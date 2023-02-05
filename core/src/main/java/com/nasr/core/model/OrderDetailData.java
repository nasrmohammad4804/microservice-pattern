package com.nasr.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailData {

    @NotBlank(message = "orderProductId is required !")
    private String productId;

    @NotNull(message = "orderQuantity is mandatory !")
    @Min(1)
    private Integer orderQuantity;
}