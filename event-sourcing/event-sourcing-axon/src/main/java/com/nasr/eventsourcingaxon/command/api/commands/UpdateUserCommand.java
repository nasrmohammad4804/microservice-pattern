package com.nasr.eventsourcingaxon.command.api.commands;

import com.nasr.eventsourcingaxon.command.api.base.command.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserCommand  extends BaseCommand<String> {

    private String firstName;
    private String lastName;
    private String password;
}
