package com.nasr.eventsourcingaxon.command.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDto {

    private String firstName;
    private String lastName;
    private String password;
}
