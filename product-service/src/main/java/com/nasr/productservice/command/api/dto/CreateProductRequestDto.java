package com.nasr.productservice.command.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequestDto {

    @NotBlank(message = "product name is mandatory")
    @Size(min = 2 , max = 100, message = "product name cant to less than two or greater than 100 character")
    private String name;

    @NotNull(message = "product price is mandatory")
    @Min(message = "product price must be equal or greater than zero", value = 0)
    private BigDecimal price;

    @Min(1)
    @NotNull(message = "product quantity is mandatory")
    private Integer quantity;
}
