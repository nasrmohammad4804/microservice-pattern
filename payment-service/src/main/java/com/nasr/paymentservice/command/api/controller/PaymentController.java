package com.nasr.paymentservice.command.api.controller;

import com.nasr.core.command.ProcessPaymentCommand;
import com.nasr.paymentservice.command.api.dto.PaymentRequestDto;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private CommandGateway commandGateway;


    @PostMapping
    public ResponseEntity<?> pay(@Valid @RequestBody PaymentRequestDto paymentRequest) {

        ProcessPaymentCommand command = ProcessPaymentCommand.builder()
                .orderId(paymentRequest.getOrderId())
                .paymentId(UUID.randomUUID().toString())
                .build();

        Object data = commandGateway.sendAndWait(command);
        return ResponseEntity.ok(data);

    }
}
