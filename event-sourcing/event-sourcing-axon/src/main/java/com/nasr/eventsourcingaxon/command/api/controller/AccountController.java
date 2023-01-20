package com.nasr.eventsourcingaxon.command.api.controller;

import com.nasr.eventsourcingaxon.command.api.dto.request.CreateAccountRequestDto;
import com.nasr.eventsourcingaxon.command.api.dto.request.UpdateBalanceAccountRequestDto;
import com.nasr.eventsourcingaxon.command.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<String> addAccount(@RequestBody @Valid CreateAccountRequestDto dto){
        accountService.save(dto);
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBalanceAccount(@RequestBody @Valid UpdateBalanceAccountRequestDto dto){
        accountService.updateBalanceAccount(dto);
        return null;
    }
}
