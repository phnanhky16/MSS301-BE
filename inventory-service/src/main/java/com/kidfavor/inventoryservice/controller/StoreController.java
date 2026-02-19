package com.kidfavor.inventoryservice.controller;

import com.kidfavor.inventoryservice.dto.StoreRequest;
import com.kidfavor.inventoryservice.dto.StoreResponse;
import com.kidfavor.inventoryservice.service.StoreService;
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
@Tag(name = "Store", description = "Store management APIs")
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    @Operation(summary = "Get all stores")
    public ResponseEntity<List<StoreResponse>> getAllStores() {
        return ResponseEntity.ok(storeService.getAllStores());
    }

    @GetMapping("/active")
    @Operation(summary = "Get all active stores")
    public ResponseEntity<List<StoreResponse>> getActiveStores() {
        return ResponseEntity.ok(storeService.getActiveStores());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get store by ID")
    public ResponseEntity<StoreResponse> getStoreById(@PathVariable Long id) {
        return ResponseEntity.ok(storeService.getStoreById(id));
    }

    @GetMapping("/code/{code}")
    @Operation(summary = "Get store by code")
    public ResponseEntity<StoreResponse> getStoreByCode(@PathVariable String code) {
        return ResponseEntity.ok(storeService.getStoreByCode(code));
    }

    @PostMapping
    @Operation(summary = "Create new store")
    public ResponseEntity<StoreResponse> createStore(@Valid @RequestBody StoreRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(storeService.createStore(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update store")
    public ResponseEntity<StoreResponse> updateStore(
            @PathVariable Long id,
            @Valid @RequestBody StoreRequest request) {
        return ResponseEntity.ok(storeService.updateStore(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete store")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.noContent().build();
    }
}
