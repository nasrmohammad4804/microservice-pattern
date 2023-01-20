package com.nasr.eventsourcingaxon.command.api.controller;

import com.nasr.eventsourcingaxon.command.api.dto.request.UpdateUserRequestDto;
import com.nasr.eventsourcingaxon.command.api.dto.response.UserResponseDto;
import com.nasr.eventsourcingaxon.command.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UpdateUserRequestDto dto){
        UserResponseDto responseDto = userService.update(id, dto);
        return ResponseEntity.ok(
                responseDto
        );
    }

}
