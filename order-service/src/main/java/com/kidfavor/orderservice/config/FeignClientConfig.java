package com.kidfavor.orderservice.config;

import feign.Logger;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Configuration for Feign clients.
 * Provides custom error handling, logging, and retry policies.
 */
@Configuration
public class FeignClientConfig {

    /**
     * Enable full logging for Feign clients in development.
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * Configure retry policy for transient failures.
     * Retries up to 3 times with exponential backoff.
     */
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(
                100,           // initial interval in ms
                TimeUnit.SECONDS.toMillis(1), // max interval
                3              // max attempts
        );
    }

    /**
     * Custom error decoder for handling Product Service errors.
     */
    @Bean
    public ErrorDecoder errorDecoder() {
        return new ProductServiceErrorDecoder();
    }
}
