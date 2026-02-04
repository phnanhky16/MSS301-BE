package com.kidfavor.userservice.repository;


import com.kidfavor.userservice.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {
    List<Shipment> findByUserId(Integer userId);

    List<Shipment> findByStatus(Boolean status);
}
