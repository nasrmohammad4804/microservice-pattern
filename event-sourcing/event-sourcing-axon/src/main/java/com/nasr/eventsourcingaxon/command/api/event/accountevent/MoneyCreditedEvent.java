package com.nasr.eventsourcingaxon.command.api.event.accountevent;

import com.nasr.eventsourcingaxon.command.api.base.event.BaseEvent;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MoneyCreditedEvent extends BaseEvent<String> {

    private Double amount;
    private String currency;

    @Builder
    public MoneyCreditedEvent(String id, Double amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}
