package com.kidfavor.orderservice.config;

import com.kidfavor.orderservice.exception.UserNotFoundException;
import com.kidfavor.orderservice.exception.UserServiceUnavailableException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Custom error decoder for User Service Feign client.
 * Translates HTTP error responses into domain-specific exceptions.
 */
@Slf4j
public class UserServiceErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultDecoder = new Default();
    private static final Pattern USER_ID_PATTERN = Pattern.compile("/users/(\\d+)");

    @Override
    public Exception decode(String methodKey, Response response) {
        String requestUrl = response.request().url();
        log.error("Error calling User Service. Method: {}, Status: {}, URL: {}", 
                methodKey, response.status(), requestUrl);

        return switch (response.status()) {
            case 404 -> {
                Long userId = extractUserId(requestUrl);
                log.warn("User not found in User Service. UserId: {}", userId);
                yield userId != null 
                    ? new UserNotFoundException(userId)
                    : new UserNotFoundException("User not found");
            }
            case 400 -> {
                log.warn("Bad request to User Service");
                yield new IllegalArgumentException("Invalid request to User Service");
            }
            case 401, 403 -> {
                log.warn("Unauthorized access to User Service");
                yield new UserServiceUnavailableException("Access denied to User Service");
            }
            case 503, 502, 504 -> {
                Long userId = extractUserId(requestUrl);
                log.error("User Service is unavailable. UserId: {}", userId);
                yield new UserServiceUnavailableException(
                    "User Service is currently unavailable" + 
                    (userId != null ? " when fetching user ID: " + userId : "")
                );
            }
            case 500 -> {
                log.error("User Service internal error");
                yield new UserServiceUnavailableException(
                    "User Service encountered an internal error"
                );
            }
            default -> {
                log.error("Unexpected error from User Service: {}", response.status());
                yield defaultDecoder.decode(methodKey, response);
            }
        };
    }

    /**
     * Extract user ID from request URL.
     * Example: http://user-service/users/123 -> 123
     */
    private Long extractUserId(String url) {
        if (url == null) return null;
        
        Matcher matcher = USER_ID_PATTERN.matcher(url);
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
