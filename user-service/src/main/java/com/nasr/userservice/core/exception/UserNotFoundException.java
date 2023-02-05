package com.nasr.userservice.core.exception;

public class UserNotFoundException extends EntityNotFoundException{

    public UserNotFoundException(String message) {
        super(message);
    }
}
