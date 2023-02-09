package com.nasr.orderservice.core.command;

import com.nasr.core.base.command.BaseCommand;
import lombok.Getter;

@Getter
public class ApproveOrderCommand extends BaseCommand<String> {


    public ApproveOrderCommand(String orderId) {
        super(orderId);
    }
}
