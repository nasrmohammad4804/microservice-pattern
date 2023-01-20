package com.nasr.eventsourcingaxon.command.api.dto.response;

import com.nasr.eventsourcingaxon.command.api.enumerate.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountWithUserInfoDto {

    private String id;

    private Double balance;

    private AccountStatus status;

    private String currency;

    private UserResponseDto userDto;
}
