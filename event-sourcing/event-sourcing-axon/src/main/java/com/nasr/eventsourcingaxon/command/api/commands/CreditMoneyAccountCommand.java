package com.nasr.eventsourcingaxon.command.api.commands;

import com.nasr.eventsourcingaxon.command.api.base.command.BaseCommand;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class CreditMoneyAccountCommand extends BaseCommand<String> {

    private String currency;

    private Double amount;

    @Builder
    public CreditMoneyAccountCommand(String id, String currency, Double amount) {
        super(id);
        this.currency = currency;
        this.amount = amount;
    }
}
