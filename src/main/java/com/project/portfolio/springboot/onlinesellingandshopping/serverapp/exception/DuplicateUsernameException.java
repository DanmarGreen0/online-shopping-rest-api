package com.project.portfolio.springboot.onlinesellingandshopping.serverapp.exception;


public class DuplicateUsernameException extends RuntimeException{

    public DuplicateUsernameException(String message) {
        super(message);
    }

    public DuplicateUsernameException(String message, Throwable cause) {
        super(message, cause);
    }
}