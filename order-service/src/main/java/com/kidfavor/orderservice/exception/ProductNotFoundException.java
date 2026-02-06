package com.kidfavor.orderservice.exception;

/**
 * Exception thrown when a requested product is not found in Product Service.
 */
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(Long productId) {
        super("Product not found with ID: " + productId);
    }
}
