package com.nasr.eventsourcingaxon.command.api.service;

import com.nasr.eventsourcingaxon.command.api.dto.request.CreateAccountRequestDto;
import com.nasr.eventsourcingaxon.command.api.dto.request.UpdateBalanceAccountRequestDto;
import com.nasr.eventsourcingaxon.command.api.dto.response.AccountWithUserInfoDto;
import com.nasr.eventsourcingaxon.command.api.dto.response.AccountResponseDto;

public interface AccountService {


    AccountWithUserInfoDto save(CreateAccountRequestDto dto);

    AccountResponseDto updateBalanceAccount(UpdateBalanceAccountRequestDto dto);
}
