package com.nasr.eventsourcingaxon.command.api.mapper;

import com.nasr.eventsourcingaxon.command.api.domain.Account;
import com.nasr.eventsourcingaxon.command.api.dto.request.CreateAccountRequestDto;
import com.nasr.eventsourcingaxon.command.api.dto.response.AccountWithUserInfoDto;
import com.nasr.eventsourcingaxon.command.api.dto.response.AccountResponseDto;
import com.nasr.eventsourcingaxon.command.api.event.accountevent.AccountCreatedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AccountMapper {

    CreateAccountRequestDto convertAccountCreatedEventToDto(AccountCreatedEvent event);


    Account convertCreateAccountDtoToEntity(CreateAccountRequestDto dto);

    @Mapping(source = "user",target = "userDto")
    AccountWithUserInfoDto convertAccountToAccountWithUserInfoDto(Account account);

    AccountResponseDto convertEntityToAccountDto(Account account);
}
