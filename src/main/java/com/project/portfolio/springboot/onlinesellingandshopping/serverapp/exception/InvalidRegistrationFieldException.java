package com.project.portfolio.springboot.onlinesellingandshopping.serverapp.exception;


public class InvalidRegistrationFieldException extends RuntimeException{

    public InvalidRegistrationFieldException(String message) {
        super(message);
    }

    public InvalidRegistrationFieldException(String message, Throwable cause) {
        super(message, cause);
    }
}