package com.nasr.eventsourcingaxon.command.api.event.accountevent;

import com.nasr.eventsourcingaxon.command.api.base.event.BaseEvent;
import com.nasr.eventsourcingaxon.command.api.enumerate.AccountStatus;
import lombok.*;

import java.math.BigDecimal;


@Getter
@NoArgsConstructor
public class AccountCreatedEvent extends BaseEvent<String> {

    private Double balance;

    private AccountStatus status;

    private String currency;

    private String userId;

    @Builder
    public AccountCreatedEvent(String id, Double balance, AccountStatus status, String currency, String userId) {
        super(id);
        this.balance = balance;
        this.status = status;
        this.currency = currency;
        this.userId = userId;
    }
}
