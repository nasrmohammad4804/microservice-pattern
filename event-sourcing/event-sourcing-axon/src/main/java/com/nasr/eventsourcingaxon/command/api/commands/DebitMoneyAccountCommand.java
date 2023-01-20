package com.nasr.eventsourcingaxon.command.api.commands;

import com.nasr.eventsourcingaxon.command.api.base.command.BaseCommand;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class DebitMoneyAccountCommand extends BaseCommand<String> {

    private String currency;

    private Double amount;

    @Builder
    public DebitMoneyAccountCommand(String id, String currency, Double amount) {
        super(id);
        this.currency = currency;
        this.amount = amount;
    }
}
