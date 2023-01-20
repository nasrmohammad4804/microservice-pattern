package com.nasr.eventsourcingaxon.command.api.dto.request;

import lombok.*;

import javax.persistence.Column;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequestDto {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

}
