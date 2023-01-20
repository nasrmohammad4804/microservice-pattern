package com.nasr.eventsourcingaxon.command.api.service;

import com.nasr.eventsourcingaxon.command.api.domain.User;
import com.nasr.eventsourcingaxon.command.api.dto.request.CreateUserRequestDto;
import com.nasr.eventsourcingaxon.command.api.dto.request.UpdateUserRequestDto;
import com.nasr.eventsourcingaxon.command.api.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {

    User getById(String userId);

    boolean existsById(String userId);

    UserResponseDto save(CreateUserRequestDto userDto);

    UserResponseDto update(String id, UpdateUserRequestDto dto);

    List<Object> getUserEventsById(String id);
}
