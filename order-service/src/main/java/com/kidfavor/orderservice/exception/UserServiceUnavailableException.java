package com.kidfavor.orderservice.exception;

/**
 * Exception thrown when User Service is unavailable or unreachable.
 */
public class UserServiceUnavailableException extends RuntimeException {

    public UserServiceUnavailableException(String message) {
        super(message);
    }

    public UserServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
