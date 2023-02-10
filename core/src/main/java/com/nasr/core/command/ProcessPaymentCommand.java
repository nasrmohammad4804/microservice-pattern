package com.nasr.core.command;

import com.nasr.core.base.command.BaseCommand;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProcessPaymentCommand extends BaseCommand<String> {

    private final String orderId;


    @Builder
    public ProcessPaymentCommand(String paymentId, String orderId) {
        super(paymentId);
        this.orderId = orderId;
    }
}
