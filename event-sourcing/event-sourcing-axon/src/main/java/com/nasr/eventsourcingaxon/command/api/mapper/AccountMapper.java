package com.nasr.eventsourcingaxon.command.api.mapper;

import com.nasr.eventsourcingaxon.command.api.domain.Account;
import com.nasr.eventsourcingaxon.command.api.dto.request.CreateAccountRequestDto;
import com.nasr.eventsourcingaxon.command.api.dto.response.AccountCreatedResponseDto;
import com.nasr.eventsourcingaxon.command.api.dto.response.UpdateBalanceAccountResponseDto;
import com.nasr.eventsourcingaxon.command.api.event.accountevent.AccountCreatedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface AccountMapper {

    CreateAccountRequestDto convertAccountCreatedEventToDto(AccountCreatedEvent event);


    Account convertCreateAccountDtoToEntity(CreateAccountRequestDto dto);

    @Mapping(source = "user",target = "userDto")
    AccountCreatedResponseDto convertAccountToAccountCreateDto(Account account);

    UpdateBalanceAccountResponseDto convertEntityToUpdateBalanceAccountDto(Account account);
}
