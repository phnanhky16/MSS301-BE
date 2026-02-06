package com.kidfavor.orderservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Async configuration for Order Service.
 * Enables async processing for event publishing.
 */
@Configuration
@EnableAsync
public class AsyncConfig {
    // Default async executor is used
    // Can be customized with @Bean ThreadPoolTaskExecutor if needed
}
