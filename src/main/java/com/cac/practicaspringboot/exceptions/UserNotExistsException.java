package com.cac.practicaspringboot.exceptions;

public class UserNotExistsException extends RuntimeException {

    public UserNotExistsException(String message) {
        super(message);
    }


}
