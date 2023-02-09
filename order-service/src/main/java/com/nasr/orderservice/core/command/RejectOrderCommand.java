package com.nasr.orderservice.core.command;

import com.nasr.core.base.command.BaseCommand;
import lombok.Getter;

@Getter
public class RejectOrderCommand extends BaseCommand<String> {

    private final String reason;

    public RejectOrderCommand(String orderId,String reason) {
        super(orderId);
        this.reason=reason;
    }
}
