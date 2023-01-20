package com.nasr.eventsourcingaxon.command.api.dto.request;

import com.nasr.eventsourcingaxon.command.api.enumerate.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequestDto {

    private Double balance;

    private AccountStatus status;

    private String currency;

    @NotNull(message = "userInfo is mandatory for creating account !")
    private CreateUserRequestDto userDto;
}
