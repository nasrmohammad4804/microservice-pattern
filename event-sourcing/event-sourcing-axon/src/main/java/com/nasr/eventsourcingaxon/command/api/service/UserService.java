package com.nasr.eventsourcingaxon.command.api.service;

import com.nasr.eventsourcingaxon.command.api.domain.User;
import com.nasr.eventsourcingaxon.command.api.dto.request.CreateUserRequestDto;
import com.nasr.eventsourcingaxon.command.api.dto.response.UserCreatedResponseDto;

public interface UserService {

    User getById(String userId);

    boolean existsById(String userId);

    UserCreatedResponseDto save(CreateUserRequestDto userDto);
}
