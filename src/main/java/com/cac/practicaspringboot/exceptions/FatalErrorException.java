package com.cac.practicaspringboot.exceptions;

public class FatalErrorException extends RuntimeException {

    public FatalErrorException(String message) {
        super(message);
    }
}
