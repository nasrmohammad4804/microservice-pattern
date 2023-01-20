package com.nasr.eventsourcingaxon.command.api.service;

import com.nasr.eventsourcingaxon.command.api.dto.request.CreateAccountRequestDto;
import com.nasr.eventsourcingaxon.command.api.dto.request.UpdateBalanceAccountRequestDto;
import com.nasr.eventsourcingaxon.command.api.dto.response.AccountCreatedResponseDto;
import com.nasr.eventsourcingaxon.command.api.dto.response.UpdateBalanceAccountResponseDto;

public interface AccountService {


    AccountCreatedResponseDto save(CreateAccountRequestDto dto);

    UpdateBalanceAccountResponseDto updateBalanceAccount(UpdateBalanceAccountRequestDto dto);
}
