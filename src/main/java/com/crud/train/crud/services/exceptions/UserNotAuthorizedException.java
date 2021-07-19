package com.crud.train.crud.services.exceptions;


public class UserNotAuthorizedException extends RuntimeException{
    private static final String MESSAGE = "User not authorized";

    public UserNotAuthorizedException() {
        super(MESSAGE);
    }
}

