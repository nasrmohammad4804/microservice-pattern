package com.nasr.eventsourcingaxon.command.api.dto.request;

import com.nasr.eventsourcingaxon.command.api.enumerate.AccountBalanceChangeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateBalanceAccountRequestDto {

    @NotNull(message = "account id for updating balance account is mandatory")
    private String id;

    private Double amount;

    private AccountBalanceChangeType accountBalanceChangeType;
}
