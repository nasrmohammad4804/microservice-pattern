package com.nasr.eventsourcingaxon.command.api.commands;

import com.nasr.eventsourcingaxon.command.api.base.command.BaseCommand;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor

public class CreateAccountCommand extends BaseCommand<String> {

    private Double balance;
    private String currency;
    private String userId;

    @Builder
    public CreateAccountCommand(String id, Double balance, String currency, String userId) {
        super(id);
        this.balance = balance;
        this.currency = currency;
        this.userId = userId;
    }
}
