package com.example.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RoleIdDoesNotExistException extends RuntimeException {
    public RoleIdDoesNotExistException(String message) {
        super(message);
    }
}
