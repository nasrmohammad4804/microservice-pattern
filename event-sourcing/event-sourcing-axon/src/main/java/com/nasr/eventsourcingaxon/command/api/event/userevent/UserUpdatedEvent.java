package com.nasr.eventsourcingaxon.command.api.event.userevent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdatedEvent {

    private String firstName;
    private String lastName;
    private String password;
}
