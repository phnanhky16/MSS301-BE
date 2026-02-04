package com.kidfavor.productservice.controller;

import com.kidfavor.productservice.dto.request.BrandCreateRequest;
import com.kidfavor.productservice.dto.request.BrandUpdateRequest;
import com.kidfavor.productservice.dto.response.BrandResponse;
import com.kidfavor.productservice.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
@Tag(name = "Brand", description = "Brand management APIs")
public class BrandController {
    
    private final BrandService brandService;
    
    @GetMapping
    @Operation(summary = "Get all brands", description = "Retrieve all brands")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved brands")
    })
    public ResponseEntity<List<BrandResponse>> getAllBrands() {
        return ResponseEntity.ok(brandService.getAllBrands());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get brand by ID", description = "Retrieve a specific brand by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Brand found"),
        @ApiResponse(responseCode = "404", description = "Brand not found")
    })
    public ResponseEntity<BrandResponse> getBrandById(
            @Parameter(description = "Brand ID") @PathVariable Long id) {
        return brandService.getBrandById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    @Operation(summary = "Create brand", description = "Create a new brand")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Brand created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<BrandResponse> createBrand(@Valid @RequestBody BrandCreateRequest request) {
        BrandResponse created = brandService.createBrand(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update brand", description = "Update an existing brand")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Brand updated successfully"),
        @ApiResponse(responseCode = "404", description = "Brand not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<BrandResponse> updateBrand(
            @Parameter(description = "Brand ID") @PathVariable Long id, 
            @Valid @RequestBody BrandUpdateRequest request) {
        BrandResponse updated = brandService.updateBrand(id, request);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete brand", description = "Delete a brand by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Brand deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Brand not found")
    })
    public ResponseEntity<Void> deleteBrand(
            @Parameter(description = "Brand ID") @PathVariable Long id) {
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
