package com.nasr.eventsourcingaxon.command.api.event.userevent;

import com.nasr.eventsourcingaxon.command.api.base.event.BaseEvent;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class UserUpdatedEvent extends BaseEvent<String> {

    private String firstName;
    private String lastName;
    private String password;

    @Builder
    public UserUpdatedEvent(String id, String firstName, String lastName, String password) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
}
