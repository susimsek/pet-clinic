package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class VetNotFoundException extends Exception {

    public VetNotFoundException(String message) {
        super(message);
    }
}
