package com.crud.train.crud.services.exceptions;

public class UserForbiddenException extends RuntimeException {
    private static final String MESSAGE = "User not allowed";
    public UserForbiddenException() {
        super(MESSAGE);
    }
}
