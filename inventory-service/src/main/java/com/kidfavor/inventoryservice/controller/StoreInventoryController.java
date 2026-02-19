package com.kidfavor.inventoryservice.controller;

import com.kidfavor.inventoryservice.dto.StockUpdateRequest;
import com.kidfavor.inventoryservice.dto.StoreInventoryRequest;
import com.kidfavor.inventoryservice.dto.StoreInventoryResponse;
import com.kidfavor.inventoryservice.service.StoreInventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
@Tag(name = "Store Inventory", description = "Store inventory management APIs")
public class StoreInventoryController {

    private final StoreInventoryService storeInventoryService;

    @GetMapping("/{storeId}/inventory")
    @Operation(summary = "Get all inventory in a store")
    public ResponseEntity<List<StoreInventoryResponse>> getInventoryByStore(@PathVariable Long storeId) {
        return ResponseEntity.ok(storeInventoryService.getInventoryByStore(storeId));
    }

    @GetMapping("/{storeId}/inventory/{productId}")
    @Operation(summary = "Get specific product inventory in a store")
    public ResponseEntity<StoreInventoryResponse> getStoreInventory(
            @PathVariable Long storeId,
            @PathVariable Long productId) {
        return ResponseEntity.ok(storeInventoryService.getStoreInventory(storeId, productId));
    }

    @GetMapping("/{storeId}/inventory/{productId}/stock")
    @Operation(summary = "Get available stock for a product in store")
    public ResponseEntity<Integer> getAvailableStock(
            @PathVariable Long storeId,
            @PathVariable Long productId) {
        return ResponseEntity.ok(storeInventoryService.getAvailableStock(storeId, productId));
    }

    @GetMapping("/{storeId}/low-stock")
    @Operation(summary = "Get low stock products in a store")
    public ResponseEntity<List<StoreInventoryResponse>> getLowStockProductsByStore(@PathVariable Long storeId) {
        return ResponseEntity.ok(storeInventoryService.getLowStockProductsByStore(storeId));
    }

    @PostMapping("/{storeId}/inventory")
    @Operation(summary = "Add or update product inventory in store")
    public ResponseEntity<StoreInventoryResponse> addOrUpdateInventory(
            @PathVariable Long storeId,
            @Valid @RequestBody StoreInventoryRequest request) {
        request.setStoreId(storeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(storeInventoryService.addOrUpdateInventory(request));
    }

    @PutMapping("/{storeId}/inventory/{productId}")
    @Operation(summary = "Update stock quantity for a product in store")
    public ResponseEntity<StoreInventoryResponse> updateStock(
            @PathVariable Long storeId,
            @PathVariable Long productId,
            @Valid @RequestBody StockUpdateRequest request) {
        request.setProductId(productId);
        return ResponseEntity.ok(storeInventoryService.updateStock(storeId, request));
    }

    @DeleteMapping("/{storeId}/inventory/{productId}")
    @Operation(summary = "Remove product from store inventory")
    public ResponseEntity<Void> removeInventory(
            @PathVariable Long storeId,
            @PathVariable Long productId) {
        storeInventoryService.removeInventory(storeId, productId);
        return ResponseEntity.noContent().build();
    }
}
