package com.nasr.productservice.command.api.controller;

import com.nasr.core.command.ReserveProductCommand;
import com.nasr.core.model.OrderDetailData;
import com.nasr.productservice.command.api.command.CreateProductCommand;
import com.nasr.productservice.command.api.dto.CreateProductRequestDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody @Valid CreateProductRequestDto request){

        CreateProductCommand command = CreateProductCommand.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();

        return ResponseEntity.ok(
                commandGateway.sendAndWait(command)
        );
    }
}
