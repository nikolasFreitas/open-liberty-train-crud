package com.crud.train.crud.services.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class TravelDateNotValidException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Travel date is not correct";

    public TravelDateNotValidException() {
        this(DEFAULT_MESSAGE);
    }

    public TravelDateNotValidException(String message) {
        super(message);
    }
}
