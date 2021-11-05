package com.sdp.project.exception;

public class UserNameExists extends RuntimeException {

    public UserNameExists(String message) {
        super(message);
    }

    public UserNameExists(String message, Throwable cause) {
        super(message, cause);
    }
}