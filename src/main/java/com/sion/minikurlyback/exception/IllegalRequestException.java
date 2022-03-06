package com.sion.minikurlyback.exception;

public class IllegalRequestException extends RuntimeException {
    public IllegalRequestException(Throwable cause) {
        super(cause);
    }

    public IllegalRequestException(String message) {
        super(message);
    }

    public IllegalRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
