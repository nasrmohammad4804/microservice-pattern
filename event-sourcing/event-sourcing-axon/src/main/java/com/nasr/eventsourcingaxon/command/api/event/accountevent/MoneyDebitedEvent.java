package com.nasr.eventsourcingaxon.command.api.event.accountevent;

import com.nasr.eventsourcingaxon.command.api.base.event.BaseEvent;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class MoneyDebitedEvent extends BaseEvent<String> {

    private Double amount;

    @Builder
    public MoneyDebitedEvent(String id, Double amount) {
        super(id);
        this.amount = amount;
    }
}
