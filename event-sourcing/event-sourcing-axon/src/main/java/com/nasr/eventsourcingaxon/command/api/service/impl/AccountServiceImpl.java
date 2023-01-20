package com.nasr.eventsourcingaxon.command.api.service.impl;

import com.nasr.eventsourcingaxon.command.api.commands.CreateAccountCommand;
import com.nasr.eventsourcingaxon.command.api.commands.CreditMoneyAccountCommand;
import com.nasr.eventsourcingaxon.command.api.commands.DebitMoneyAccountCommand;
import com.nasr.eventsourcingaxon.command.api.domain.Account;
import com.nasr.eventsourcingaxon.command.api.domain.User;
import com.nasr.eventsourcingaxon.command.api.dto.request.CreateAccountRequestDto;
import com.nasr.eventsourcingaxon.command.api.dto.request.UpdateBalanceAccountRequestDto;
import com.nasr.eventsourcingaxon.command.api.dto.response.AccountWithUserInfoDto;
import com.nasr.eventsourcingaxon.command.api.dto.response.AccountResponseDto;
import com.nasr.eventsourcingaxon.command.api.dto.response.UserResponseDto;
import com.nasr.eventsourcingaxon.command.api.exception.EntityNotFoundException;
import com.nasr.eventsourcingaxon.command.api.mapper.AccountMapper;
import com.nasr.eventsourcingaxon.command.api.repository.AccountRepository;
import com.nasr.eventsourcingaxon.command.api.service.AccountService;
import com.nasr.eventsourcingaxon.command.api.service.UserService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.nasr.eventsourcingaxon.command.api.enumerate.AccountBalanceChangeType.CREDITED;
import static com.nasr.eventsourcingaxon.command.api.enumerate.AccountBalanceChangeType.DEBITED;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {


    private AccountRepository repository;

    private UserService userService;

    private AccountMapper accountMapper;

    private final CommandGateway commandGateway;

    private final EventStore eventStore;

    public AccountServiceImpl(AccountRepository repository, UserService userService,
                              AccountMapper accountMapper, CommandGateway gateway, EventStore eventStore) {

        this.repository = repository;
        this.userService = userService;
        this.accountMapper = accountMapper;
        this.commandGateway = gateway;
        this.eventStore = eventStore;
    }

    @Override
    @Transactional
    public AccountWithUserInfoDto save(CreateAccountRequestDto dto) {
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

            UserResponseDto userDto = userService.save(dto.getUserDto());
            account.setUser(new User(userDto.getId()));
            repository.save(account);

            command.setUserId(userDto.getId());
            commandGateway.send(command);

        }

        return accountMapper.convertAccountToAccountWithUserInfoDto(account);

    }

    @Override
    @Transactional
    public AccountResponseDto updateBalanceAccount(UpdateBalanceAccountRequestDto dto) {

        Account account = repository.findById(dto.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException("dont find any account with id : " + dto.getId())
                );

        if (dto.getAccountBalanceChangeType().equals(CREDITED)) {
            account.setBalance(account.getBalance() + dto.getAmount());
            commandGateway.send(
                    CreditMoneyAccountCommand.builder()
                            .id(dto.getId())
                            .amount(dto.getAmount())
                            .build()
            );
        } else if (dto.getAccountBalanceChangeType().equals(DEBITED)) {
            account.setBalance(account.getBalance() - dto.getAmount());
            commandGateway.send(
                    DebitMoneyAccountCommand.builder()
                            .id(dto.getId())
                            .amount(dto.getAmount())
                            .build()
            );
        }

        return accountMapper.convertEntityToAccountDto(account);
    }

    @Override
    public List<Object> getEventsById(String id) {

        return eventStore.readEvents(id)
                .asStream()
                .map(Message::getPayload)
                .collect(Collectors.toList());
    }
}
