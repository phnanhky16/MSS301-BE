package com.kidfavor.userservice.service;

import com.kidfavor.userservice.dto.request.shipment.ShipmentCreateRequest;
import com.kidfavor.userservice.dto.request.shipment.ShipmentUpdateRequest;
import com.kidfavor.userservice.dto.response.ShipmentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShipmentService {
     List<ShipmentResponse> getAllShipments();
     ShipmentResponse getShipmentById(int id);
     List<ShipmentResponse> getShipmentsByUserId(int userId);
     List<ShipmentResponse> getShipmentByStatus(Boolean status);
     ShipmentResponse createShipment(ShipmentCreateRequest request);
     ShipmentResponse updateShipment(int id,ShipmentUpdateRequest request);
     void changeShipmentStatus(int id);

}
