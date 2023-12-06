package com.cac.practicaspringboot.exceptions;

public class EntityNotExistsException extends RuntimeException {

    public EntityNotExistsException(String message) {
        super(message);
    }


}
