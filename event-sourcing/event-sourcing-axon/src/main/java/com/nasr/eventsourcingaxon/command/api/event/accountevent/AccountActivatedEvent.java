package com.nasr.eventsourcingaxon.command.api.event.accountevent;

import com.nasr.eventsourcingaxon.command.api.base.event.BaseEvent;
import com.nasr.eventsourcingaxon.command.api.enumerate.AccountStatus;
import lombok.*;


@Getter
@NoArgsConstructor
public class AccountActivatedEvent extends BaseEvent<String> {

    private AccountStatus status;

    @Builder
    public AccountActivatedEvent(String id, AccountStatus status) {
        super(id);
        this.status = status;
    }
}
