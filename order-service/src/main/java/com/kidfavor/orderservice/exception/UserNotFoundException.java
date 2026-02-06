package com.kidfavor.orderservice.exception;

/**
 * Exception thrown when a requested user is not found in User Service.
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long userId) {
        super("User not found with ID: " + userId);
    }
}
