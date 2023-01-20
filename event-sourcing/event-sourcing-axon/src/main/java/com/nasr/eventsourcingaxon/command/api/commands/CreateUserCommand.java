package com.nasr.eventsourcingaxon.command.api.commands;

import com.nasr.eventsourcingaxon.command.api.base.command.BaseCommand;
import lombok.*;

import javax.persistence.Column;

@Setter
@Getter
@NoArgsConstructor

public class CreateUserCommand extends BaseCommand<String> {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @Builder
    public CreateUserCommand(String id, String firstName, String lastName, String email, String password) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
