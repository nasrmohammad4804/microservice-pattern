package com.nasr.eventsourcingaxon.command.api.service.impl;

import com.nasr.eventsourcingaxon.command.api.commands.CreateAccountCommand;
import com.nasr.eventsourcingaxon.command.api.commands.CreditMoneyAccountCommand;
import com.nasr.eventsourcingaxon.command.api.commands.DebitMoneyAccountCommand;
import com.nasr.eventsourcingaxon.command.api.domain.Account;
import com.nasr.eventsourcingaxon.command.api.domain.User;
import com.nasr.eventsourcingaxon.command.api.dto.request.CreateAccountRequestDto;
import com.nasr.eventsourcingaxon.command.api.dto.request.UpdateBalanceAccountRequestDto;
import com.nasr.eventsourcingaxon.command.api.dto.response.AccountCreatedResponseDto;
import com.nasr.eventsourcingaxon.command.api.dto.response.UpdateBalanceAccountResponseDto;
import com.nasr.eventsourcingaxon.command.api.dto.response.UserCreatedResponseDto;
import com.nasr.eventsourcingaxon.command.api.exception.EntityNotFoundException;
import com.nasr.eventsourcingaxon.command.api.mapper.AccountMapper;
import com.nasr.eventsourcingaxon.command.api.repository.AccountRepository;
import com.nasr.eventsourcingaxon.command.api.service.AccountService;
import com.nasr.eventsourcingaxon.command.api.service.UserService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.nasr.eventsourcingaxon.command.api.enumerate.AccountBalanceChangeType.CREDITED;
import static com.nasr.eventsourcingaxon.command.api.enumerate.AccountBalanceChangeType.DEBITED;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {


    private AccountRepository repository;

    private UserService userService;

    private AccountMapper accountMapper;

    private final CommandGateway commandGateway;

    public AccountServiceImpl(AccountRepository repository, UserService userService, AccountMapper accountMapper,CommandGateway gateway) {
        this.repository = repository;
        this.userService = userService;
        this.accountMapper = accountMapper;
        this.commandGateway =gateway;
    }

    @Override
    @Transactional
    public AccountCreatedResponseDto save(CreateAccountRequestDto dto) {
        Account account = accountMapper.convertCreateAccountDtoToEntity(dto);
        String accountId = UUID.randomUUID().toString();

        account.setId(accountId);

        CreateAccountCommand command = CreateAccountCommand.builder()
                .id(accountId)
                .balance(dto.getBalance())
                .currency(dto.getCurrency())
                .build();

        String userId = dto.getUserDto().getId();

        boolean existsUserById = userService.existsById(userId);

        if (existsUserById) {
            User user = userService.getById(userId);
            account.setUser(user);
            repository.save(account);

            command.setUserId(userId);
            commandGateway.send(command);

        } else {

            UserCreatedResponseDto userDto = userService.save(dto.getUserDto());
            account.setUser(new User(userDto.getId()));
            repository.save(account);

            command.setUserId(userDto.getId());
            commandGateway.send(command);

        }

        return accountMapper.convertAccountToAccountCreateDto(account);

    }

    @Override
    @Transactional
    public UpdateBalanceAccountResponseDto updateBalanceAccount(UpdateBalanceAccountRequestDto dto) {

        Account account = repository.findById(dto.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException("dont find any account with id : " + dto.getId())
                );

        if (dto.getAccountBalanceChangeType().equals(CREDITED)){
            account.setBalance(account.getBalance() + dto.getAmount());
            commandGateway.send(
                    CreditMoneyAccountCommand.builder()
                            .id(dto.getId())
                            .amount(dto.getAmount())
                            .build()
            );
        }

        else if (dto.getAccountBalanceChangeType().equals(DEBITED)){
            account.setBalance(account.getBalance() - dto.getAmount());
            commandGateway.send(
                    DebitMoneyAccountCommand.builder()
                            .id(dto.getId())
                            .amount(dto.getAmount())
                            .build()
            );
        }

        return accountMapper.convertEntityToUpdateBalanceAccountDto(account);
    }
}
