package com.kidfavor.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "shipments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(nullable = false)
    private Long orderId; // Reference to Order in order-service
    
    @Column(nullable = false)
    private String shippingAddress;
    
    private String trackingNumber;
    
    @Column(nullable = false)
    private String status; // PENDING, SHIPPED, IN_TRANSIT, DELIVERED, CANCELLED
    
    private String carrier; // Shipping company
    
    private LocalDateTime shippedAt;
    
    private LocalDateTime deliveredAt;
    
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
