package com.nasr.paymentservice.command.api.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {

    @NotBlank(message = "orderId is required")
    private  String orderId;
}
