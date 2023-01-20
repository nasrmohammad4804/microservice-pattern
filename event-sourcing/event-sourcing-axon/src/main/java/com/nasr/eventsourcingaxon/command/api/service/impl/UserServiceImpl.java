package com.nasr.eventsourcingaxon.command.api.service.impl;

import com.nasr.eventsourcingaxon.command.api.commands.CreateUserCommand;
import com.nasr.eventsourcingaxon.command.api.domain.User;
import com.nasr.eventsourcingaxon.command.api.dto.request.CreateUserRequestDto;
import com.nasr.eventsourcingaxon.command.api.dto.response.UserCreatedResponseDto;
import com.nasr.eventsourcingaxon.command.api.exception.UserNotFoundException;
import com.nasr.eventsourcingaxon.command.api.mapper.UserMapper;
import com.nasr.eventsourcingaxon.command.api.repository.UserRepository;
import com.nasr.eventsourcingaxon.command.api.service.UserService;
import com.nasr.eventsourcingaxon.command.api.service.validator.UserRegistrationValidationResult;
import com.nasr.eventsourcingaxon.command.api.service.validator.UserRegistrationValidator;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.UUID;

import static com.nasr.eventsourcingaxon.command.api.service.validator.UserRegistrationValidationResult.OK;
import static com.nasr.eventsourcingaxon.command.api.service.validator.UserRegistrationValidator.*;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    private UserMapper userMapper;

    private final CommandGateway commandGateway;

    public UserServiceImpl(UserRepository repository, UserMapper userMapper, CommandGateway gateway) {
        this.repository = repository;
        this.userMapper = userMapper;
        this.commandGateway=gateway;
    }

    @Override
    public User getById(String userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("dont find any user with id : "+userId));
    }

    @Override
    public boolean existsById(String userId) {
        return repository.existsById(userId);
    }

    @Override
    @Transactional
    public UserCreatedResponseDto save(CreateUserRequestDto userDto) {

        boolean existsByEmail = repository.existsByEmail(userDto.getEmail());

        if (existsByEmail)
            throw new IllegalStateException("already taken user with email  : "+userDto.getEmail());

        User user = userMapper.convertCreateUserDtoToEntity(userDto);

        UserRegistrationValidationResult validationResult = verifyFirstName()
                .and(verifyLastName())
                .and(verifyEmail())
                .and(verifyPassword())
                .apply(user);

        if (!validationResult.equals(OK))
            throw new IllegalStateException(validationResult.name());

        user.setId(UUID.randomUUID().toString());

        repository.save(user);

        commandGateway.send(
                CreateUserCommand.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .password(user.getPassword())
                        .build()

        );
        return userMapper.convertEntityToCreateUserDto(user);
    }
}
