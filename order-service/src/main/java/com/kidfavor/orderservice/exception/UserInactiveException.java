package com.kidfavor.orderservice.exception;

/**
 * Exception thrown when a user account is inactive.
 */
public class UserInactiveException extends RuntimeException {

    public UserInactiveException(Long userId) {
        super("User with ID " + userId + " is inactive and cannot place orders");
    }

    public UserInactiveException(String message) {
        super(message);
    }
}
