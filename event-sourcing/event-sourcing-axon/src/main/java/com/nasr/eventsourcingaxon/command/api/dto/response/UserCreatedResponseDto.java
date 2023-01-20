package com.nasr.eventsourcingaxon.command.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedResponseDto {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

}
