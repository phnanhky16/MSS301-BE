package com.kidfavor.userservice.controller;

import com.kidfavor.userservice.dto.ApiResponse;
import com.kidfavor.userservice.dto.request.shipment.ShipmentCreateRequest;
import com.kidfavor.userservice.dto.request.shipment.ShipmentUpdateRequest;
import com.kidfavor.userservice.dto.response.ShipmentResponse;
import com.kidfavor.userservice.service.ShipmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Shipment Management", description = "APIs for managing shipments")
public class ShipmentController {
    
    ShipmentService shipmentService;
    @Operation(
            summary = "Get all shipments",
            description = "Retrieve a list of all shipments in the system"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved list of shipments",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    @GetMapping
    public ResponseEntity<ApiResponse<List<ShipmentResponse>>> getAllShipments() {
        List<ShipmentResponse> shipments = shipmentService.getAllShipments();
        return ResponseEntity.ok(ApiResponse.success("Retrieved all shipments successfully", shipments));
    }

    @Operation(
            summary = "Get shipment by ID",
            description = "Retrieve a specific shipment by its ID"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved shipment"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Shipment not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ShipmentResponse>> getShipmentById(
            @Parameter(description = "Shipment ID", required = true)
            @PathVariable Integer id) {
        ShipmentResponse shipment = shipmentService.getShipmentById(id);
        return ResponseEntity.ok(ApiResponse.success("Retrieved shipment successfully", shipment));
    }

    @Operation(
            summary = "Get shipments by user ID",
            description = "Retrieve all shipments for a specific user"
    )
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<ShipmentResponse>>> getShipmentsByUserId(
            @Parameter(description = "User ID", required = true)
            @PathVariable Integer userId) {
        List<ShipmentResponse> shipments = shipmentService.getShipmentsByUserId(userId);
        return ResponseEntity.ok(ApiResponse.success("Retrieved user shipments successfully", shipments));
    }

    @Operation(
            summary = "Get shipments by status",
            description = "Retrieve all shipments filtered by their delivery status"
    )
    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<ShipmentResponse>>> getShipmentsByStatus(
            @Parameter(description = "Shipment status (true=delivered, false=pending)", required = true)
            @PathVariable Boolean status) {
        List<ShipmentResponse> shipments = shipmentService.getShipmentByStatus(status);
        return ResponseEntity.ok(ApiResponse.success("Retrieved shipments by status successfully", shipments));
    }

    @Operation(
            summary = "Create new shipment",
            description = "Create a new shipment for a user"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "Shipment created successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data"
            )
    })
    @PostMapping
    public ResponseEntity<ApiResponse<ShipmentResponse>> createShipment(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Shipment creation data",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ShipmentCreateRequest.class))
            )
            @RequestBody ShipmentCreateRequest request) {
        ShipmentResponse shipment = shipmentService.createShipment(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Shipment created successfully", shipment));
    }

    @Operation(
            summary = "Update shipment",
            description = "Update an existing shipment's information"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Shipment updated successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Shipment not found"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ShipmentResponse>> updateShipment(
            @Parameter(description = "Shipment ID", required = true)
            @PathVariable Integer id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Shipment update data",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ShipmentUpdateRequest.class))
            )
            @RequestBody ShipmentUpdateRequest request) {
        ShipmentResponse shipment = shipmentService.updateShipment(id, request);
        return ResponseEntity.ok(ApiResponse.success("Shipment updated successfully", shipment));
    }

    @Operation(
            summary = "Delete shipment",
            description = "Delete a shipment from the system"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Shipment deleted successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Shipment not found"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> changeShipmentStatus(
            @Parameter(description = "Shipment ID", required = true)
            @PathVariable Integer id) {
        shipmentService.changeShipmentStatus(id);
        return ResponseEntity.ok(ApiResponse.success("Shipment changed status successfully", null));
    }

}
