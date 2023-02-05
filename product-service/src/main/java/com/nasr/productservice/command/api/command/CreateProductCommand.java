package com.nasr.productservice.command.api.command;

import com.nasr.core.base.command.BaseCommand;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter

public class CreateProductCommand extends BaseCommand<String> {

    private final String name;
    private final BigDecimal price;
    private final Integer quantity;

    @Builder
    public CreateProductCommand(String id, String name, BigDecimal price, Integer quantity) {
        super(id);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
