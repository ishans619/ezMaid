package com.ezmaid.exception;

@SuppressWarnings("serial")
public class RequestFinalizeException extends RuntimeException {

    public RequestFinalizeException(String message) {
        super(message);
    }
}
