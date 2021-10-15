package com.example.mydinner.rest.exception;

public class ResourceRestBadRequestException extends RuntimeException{

    public ResourceRestBadRequestException(String message) {
        super(message);
    }

    public ResourceRestBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceRestBadRequestException(Throwable cause) {
        super(cause);
    }
}
