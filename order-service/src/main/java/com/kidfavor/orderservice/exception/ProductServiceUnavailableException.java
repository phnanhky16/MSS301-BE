package com.kidfavor.orderservice.exception;

/**
 * Exception thrown when Product Service is unavailable.
 */
public class ProductServiceUnavailableException extends RuntimeException {

    public ProductServiceUnavailableException(String message) {
        super(message);
    }
}
