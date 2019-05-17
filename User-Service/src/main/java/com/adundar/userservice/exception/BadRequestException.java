package com.adundar.userservice.exception;

public class BadRequestException extends Exception {

    private static final long serialVersionUID = 3753443693023266293L;

    public BadRequestException(String message) {
        super(message);
    }

}
