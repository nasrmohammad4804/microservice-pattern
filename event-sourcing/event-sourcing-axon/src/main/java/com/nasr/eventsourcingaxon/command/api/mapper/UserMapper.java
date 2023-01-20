package com.nasr.eventsourcingaxon.command.api.mapper;

import com.nasr.eventsourcingaxon.command.api.domain.User;
import com.nasr.eventsourcingaxon.command.api.dto.request.CreateUserRequestDto;
import com.nasr.eventsourcingaxon.command.api.dto.response.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User convertCreateUserDtoToEntity(CreateUserRequestDto dto);

    UserResponseDto convertEntityToUserDto(User user);
}
