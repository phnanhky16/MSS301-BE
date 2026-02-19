package com.kidfavor.inventoryservice.controller;

import com.kidfavor.inventoryservice.dto.StockUpdateRequest;
import com.kidfavor.inventoryservice.dto.WarehouseProductRequest;
import com.kidfavor.inventoryservice.dto.WarehouseProductResponse;
import com.kidfavor.inventoryservice.service.WarehouseProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
@RequiredArgsConstructor
@Tag(name = "Warehouse Inventory", description = "Warehouse inventory management APIs")
public class WarehouseProductController {

    private final WarehouseProductService warehouseProductService;

    @GetMapping("/{warehouseId}/products")
    @Operation(summary = "Get all products in a warehouse")
    public ResponseEntity<List<WarehouseProductResponse>> getProductsByWarehouse(@PathVariable Long warehouseId) {
        return ResponseEntity.ok(warehouseProductService.getProductsByWarehouse(warehouseId));
    }

    @GetMapping("/{warehouseId}/products/{productId}")
    @Operation(summary = "Get specific product in a warehouse")
    public ResponseEntity<WarehouseProductResponse> getWarehouseProduct(
            @PathVariable Long warehouseId,
            @PathVariable Long productId) {
        return ResponseEntity.ok(warehouseProductService.getWarehouseProduct(warehouseId, productId));
    }

    @GetMapping("/{warehouseId}/products/{productId}/stock")
    @Operation(summary = "Get available stock for a product in warehouse")
    public ResponseEntity<Integer> getAvailableStock(
            @PathVariable Long warehouseId,
            @PathVariable Long productId) {
        return ResponseEntity.ok(warehouseProductService.getAvailableStock(warehouseId, productId));
    }

    @GetMapping("/{warehouseId}/low-stock")
    @Operation(summary = "Get low stock products in a warehouse")
    public ResponseEntity<List<WarehouseProductResponse>> getLowStockProductsByWarehouse(@PathVariable Long warehouseId) {
        return ResponseEntity.ok(warehouseProductService.getLowStockProductsByWarehouse(warehouseId));
    }

    @PostMapping("/{warehouseId}/products")
    @Operation(summary = "Add or update product in warehouse")
    public ResponseEntity<WarehouseProductResponse> addOrUpdateProduct(
            @PathVariable Long warehouseId,
            @Valid @RequestBody WarehouseProductRequest request) {
        request.setWarehouseId(warehouseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(warehouseProductService.addOrUpdateProduct(request));
    }

    @PutMapping("/{warehouseId}/products/{productId}")
    @Operation(summary = "Update stock quantity for a product in warehouse")
    public ResponseEntity<WarehouseProductResponse> updateStock(
            @PathVariable Long warehouseId,
            @PathVariable Long productId,
            @Valid @RequestBody StockUpdateRequest request) {
        request.setProductId(productId);
        return ResponseEntity.ok(warehouseProductService.updateStock(warehouseId, request));
    }

    @DeleteMapping("/{warehouseId}/products/{productId}")
    @Operation(summary = "Remove product from warehouse")
    public ResponseEntity<Void> removeProduct(
            @PathVariable Long warehouseId,
            @PathVariable Long productId) {
        warehouseProductService.removeProduct(warehouseId, productId);
        return ResponseEntity.noContent().build();
    }
}
