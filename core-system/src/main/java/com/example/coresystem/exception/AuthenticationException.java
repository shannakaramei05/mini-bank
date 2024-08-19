package com.example.coresystem.exception;

public class AuthenticationException extends RuntimeException{

    private static final long serialVersionUID = -7097924932914325594L;

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
