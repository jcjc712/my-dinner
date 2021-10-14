package com.example.mydinner.rest.exception;

public class ResourceRestNotFoundException extends RuntimeException{

    public ResourceRestNotFoundException(String message) {
        super(message);
    }

    public ResourceRestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceRestNotFoundException(Throwable cause) {
        super(cause);
    }
}
