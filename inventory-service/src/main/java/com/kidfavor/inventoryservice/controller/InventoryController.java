package com.kidfavor.inventoryservice.controller;

import com.kidfavor.inventoryservice.dto.StoreInventoryResponse;
import com.kidfavor.inventoryservice.dto.WarehouseProductResponse;
import com.kidfavor.inventoryservice.service.StoreInventoryService;
import com.kidfavor.inventoryservice.service.WarehouseProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Tag(name = "Inventory", description = "General inventory APIs")
public class InventoryController {

    private final WarehouseProductService warehouseProductService;
    private final StoreInventoryService storeInventoryService;

    @GetMapping("/low-stock")
    @Operation(summary = "Get all low stock products across warehouses and stores")
    public ResponseEntity<Map<String, Object>> getAllLowStockProducts() {
        List<WarehouseProductResponse> warehouseLowStock = warehouseProductService.getLowStockProducts();
        List<StoreInventoryResponse> storeLowStock = storeInventoryService.getLowStockProducts();
        
        Map<String, Object> response = new HashMap<>();
        response.put("warehouses", warehouseLowStock);
        response.put("stores", storeLowStock);
        response.put("totalWarehouseProducts", warehouseLowStock.size());
        response.put("totalStoreProducts", storeLowStock.size());
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    @Operation(summary = "Health check endpoint")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "inventory-service");
        return ResponseEntity.ok(response);
    }
}
