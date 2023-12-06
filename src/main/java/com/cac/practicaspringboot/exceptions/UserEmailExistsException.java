package com.cac.practicaspringboot.exceptions;

public class UserEmailExistsException extends RuntimeException {

    public UserEmailExistsException(String message) {
        super(message);
    }
}
