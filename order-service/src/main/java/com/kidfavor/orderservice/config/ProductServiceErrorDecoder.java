package com.kidfavor.orderservice.config;

import com.kidfavor.orderservice.exception.ProductNotFoundException;
import com.kidfavor.orderservice.exception.ProductServiceUnavailableException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Custom error decoder for Product Service Feign client.
 * Translates HTTP error responses into domain-specific exceptions.
 */
@Slf4j
public class ProductServiceErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultDecoder = new Default();
    private static final Pattern PRODUCT_ID_PATTERN = Pattern.compile("/products/(\\d+)");

    @Override
    public Exception decode(String methodKey, Response response) {
        String requestUrl = response.request().url();
        log.error("Error calling Product Service. Method: {}, Status: {}, URL: {}", 
                methodKey, response.status(), requestUrl);

        return switch (response.status()) {
            case 404 -> {
                Long productId = extractProductId(requestUrl);
                log.warn("Product not found in Product Service. ProductId: {}", productId);
                yield productId != null 
                    ? new ProductNotFoundException(productId)
                    : new ProductNotFoundException("Product not found");
            }
            case 400 -> {
                log.warn("Bad request to Product Service");
                yield new IllegalArgumentException("Invalid request to Product Service");
            }
            case 503, 502, 504 -> {
                Long productId = extractProductId(requestUrl);
                log.error("Product Service is unavailable. ProductId: {}", productId);
                yield new ProductServiceUnavailableException(
                    "Product Service is currently unavailable" + 
                    (productId != null ? " when fetching product ID: " + productId : "")
                );
            }
            case 500 -> {
                log.error("Product Service internal error");
                yield new ProductServiceUnavailableException(
                    "Product Service encountered an internal error"
                );
            }
            default -> {
                log.error("Unexpected error from Product Service: {}", response.status());
                yield defaultDecoder.decode(methodKey, response);
            }
        };
    }

    /**
     * Extract product ID from request URL.
     * Example: http://product-service/products/123 -> 123
     */
    private Long extractProductId(String url) {
        if (url == null) return null;
        
        Matcher matcher = PRODUCT_ID_PATTERN.matcher(url);
        if (matcher.find()) {
            try {
                return Long.parseLong(matcher.group(1));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }
}
