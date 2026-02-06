package com.kidfavor.orderservice.exception;

/**
 * Exception thrown when a product is inactive and cannot be ordered.
 */
public class ProductInactiveException extends RuntimeException {

    public ProductInactiveException(Long productId) {
        super("Product with ID " + productId + " is not active and cannot be ordered");
    }
}
