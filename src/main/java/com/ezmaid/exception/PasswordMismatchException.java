package com.ezmaid.exception;

@SuppressWarnings("serial")
public class PasswordMismatchException extends RuntimeException {

    public PasswordMismatchException(String message) {
        super(message);
    }
}
