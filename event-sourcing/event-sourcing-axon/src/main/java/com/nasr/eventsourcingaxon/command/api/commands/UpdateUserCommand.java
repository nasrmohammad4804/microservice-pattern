package com.nasr.eventsourcingaxon.command.api.commands;

import com.nasr.eventsourcingaxon.command.api.base.command.BaseCommand;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class UpdateUserCommand  extends BaseCommand<String> {

    private String firstName;
    private String lastName;
    private String password;

    @Builder
    public UpdateUserCommand(String id, String firstName, String lastName, String password) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
}
