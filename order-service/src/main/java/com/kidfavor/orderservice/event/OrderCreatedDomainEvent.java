package com.kidfavor.orderservice.event;

import com.kidfavor.orderservice.entity.Order;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Domain event that wraps OrderPlacedEvent for Spring's event mechanism.
 * This event is published within the application and then forwarded to Kafka.
 */
@Getter
public class OrderCreatedDomainEvent extends ApplicationEvent {

    private final Order order;
    private final String customerEmail;
    private final String customerName;

    public OrderCreatedDomainEvent(Object source, Order order, String customerEmail, String customerName) {
        super(source);
        this.order = order;
        this.customerEmail = customerEmail;
        this.customerName = customerName;
    }
}
