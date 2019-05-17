package com.adundar.messageservice.exception;

public class AlreadyExistException extends Exception {

    private static final long serialVersionUID = 8536763612809648069L;

    public AlreadyExistException(String message) {
        super(message);
    }

}

