package com.nasr.eventsourcingaxon.command.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto {

    private String id;

    private Double balance;

    private String currency;
}
