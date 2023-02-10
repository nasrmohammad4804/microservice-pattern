package com.nasr.orderservice.command.api.controller;

import com.nasr.orderservice.command.api.dto.PlaceOrderRequestDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.nasr.orderservice.core.util.OrderConverterUtility.convertPlaceOrderRequestToCommand;

@RestController
@RequestMapping("/orders")
public class OrderCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody @Valid PlaceOrderRequestDto dto) {
        Object data = commandGateway.sendAndWait(convertPlaceOrderRequestToCommand(dto));
        return ResponseEntity.ok(data);
    }

}
