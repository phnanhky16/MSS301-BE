package com.kidfavor.userservice.service;

import com.kidfavor.userservice.entity.Shipment;
import com.kidfavor.userservice.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ShipmentService {
    
    private final ShipmentRepository shipmentRepository;
    
    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }
    
    public Optional<Shipment> getShipmentById(Long id) {
        return shipmentRepository.findById(id);
    }
    
    public List<Shipment> getShipmentsByUserId(Long userId) {
        return shipmentRepository.findByUserId(userId);
    }
    
    public List<Shipment> getShipmentsByOrderId(Long orderId) {
        return shipmentRepository.findByOrderId(orderId);
    }
    
    public Optional<Shipment> getShipmentByTrackingNumber(String trackingNumber) {
        return shipmentRepository.findByTrackingNumber(trackingNumber);
    }
    
    public Shipment createShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }
    
    public Shipment updateShipment(Long id, Shipment shipmentDetails) {
        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shipment not found with id: " + id));
        
        shipment.setShippingAddress(shipmentDetails.getShippingAddress());
        shipment.setTrackingNumber(shipmentDetails.getTrackingNumber());
        shipment.setStatus(shipmentDetails.getStatus());
        shipment.setCarrier(shipmentDetails.getCarrier());
        shipment.setShippedAt(shipmentDetails.getShippedAt());
        shipment.setDeliveredAt(shipmentDetails.getDeliveredAt());
        
        return shipmentRepository.save(shipment);
    }
    
    public void deleteShipment(Long id) {
        shipmentRepository.deleteById(id);
    }
}
