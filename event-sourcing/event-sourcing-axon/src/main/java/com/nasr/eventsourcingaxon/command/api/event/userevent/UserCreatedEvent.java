package com.nasr.eventsourcingaxon.command.api.event.userevent;

import com.nasr.eventsourcingaxon.command.api.base.event.BaseEvent;
import lombok.*;

import javax.persistence.Column;

@Setter
@Getter
@NoArgsConstructor
public class UserCreatedEvent extends BaseEvent<String> {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @Builder
    public UserCreatedEvent(String id, String firstName, String lastName, String email, String password) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
