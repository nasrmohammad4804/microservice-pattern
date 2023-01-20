package com.nasr.eventsourcingaxon.command.api.service.validator;

import lombok.Getter;

@Getter
public enum UserRegistrationValidationResult {

    FIRST_NAME_NOT_VALID,LAST_NAME_NOT_VALID,EMAIL_NOT_VALID,PASSWORD_NOT_VALID,OK
}
