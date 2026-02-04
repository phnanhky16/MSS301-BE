package com.kidfavor.userservice.controller;

import com.kidfavor.userservice.dto.ApiResponse;
import com.kidfavor.userservice.dto.response.UserResponse;
import com.kidfavor.userservice.entity.enums.Role;
import com.kidfavor.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Admin Management", description = "APIs for admin operations (Requires ADMIN role)")
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    UserService userService;

    @Operation(
            summary = "Get all users (Admin)",
            description = "Retrieve a list of all users in the system. Only accessible by ADMIN role."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved list of users",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - JWT token missing or invalid"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - User does not have ADMIN role"
            )
    })
    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(ApiResponse.success("Retrieved all users successfully", users));
    }

    @Operation(
            summary = "Get user by ID (Admin)",
            description = "Retrieve a specific user by their ID. Only accessible by ADMIN role."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved user",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - JWT token missing or invalid"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - User does not have ADMIN role"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    @GetMapping("/users/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(
            @Parameter(description = "User ID", required = true)
            @PathVariable Integer id) {
        UserResponse user = userService.getUserById(id);
        return ResponseEntity.ok(ApiResponse.success("Retrieved user successfully", user));
    }

    @Operation(
            summary = "Change user role (Admin)",
            description = "Change the role of a user. Only accessible by ADMIN role."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "User role updated successfully",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - JWT token missing or invalid"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - User does not have ADMIN role"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    @PutMapping("/users/{id}/role")
    public ResponseEntity<ApiResponse<UserResponse>> changeUserRole(
            @Parameter(description = "User ID", required = true)
            @PathVariable Integer id,
            @Parameter(description = "New role for the user", required = true)
            @RequestParam Role role) {
        UserResponse user = userService.changeUserRole(id, role);
        return ResponseEntity.ok(ApiResponse.success("User role updated successfully", user));
    }

    @Operation(
            summary = "Activate/Deactivate user (Admin)",
            description = "Toggle user active status. Only accessible by ADMIN role."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "User status changed successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - JWT token missing or invalid"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - User does not have ADMIN role"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    @PutMapping("/users/{id}/status")
    public ResponseEntity<ApiResponse<Void>> toggleUserStatus(
            @Parameter(description = "User ID", required = true)
            @PathVariable Integer id) {
        userService.changeUserStatus(id);
        return ResponseEntity.ok(ApiResponse.success("User status changed successfully", null));
    }

    @Operation(
            summary = "Delete user permanently (Admin)",
            description = "Permanently delete a user from the system. Only accessible by ADMIN role."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "User deleted successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - JWT token missing or invalid"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - User does not have ADMIN role"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    @DeleteMapping("/users/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(
            @Parameter(description = "User ID", required = true)
            @PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(ApiResponse.success("User deleted successfully", null));
    }

    @Operation(
            summary = "Get users by role (Admin)",
            description = "Retrieve all users with a specific role. Only accessible by ADMIN role."
    )
    @GetMapping("/users/role/{role}")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getUsersByRole(
            @Parameter(description = "User role (CUSTOMER, STAFF_FOR_STORE, STAFF_FOR_WAREHOUSE, ADMIN)", required = true)
            @PathVariable Role role) {
        List<UserResponse> users = userService.getUsersByRole(role);
        return ResponseEntity.ok(ApiResponse.success("Retrieved users by role successfully", users));
    }

    @Operation(
            summary = "Get users by status (Admin)",
            description = "Retrieve all users filtered by their active/inactive status. Only accessible by ADMIN role."
    )
    @GetMapping("/users/status/{status}")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getUsersByStatus(
            @Parameter(description = "User status (true=active, false=inactive)", required = true)
            @PathVariable Boolean status) {
        List<UserResponse> users = userService.getUsersByStatus(status);
        return ResponseEntity.ok(ApiResponse.success("Retrieved users by status successfully", users));
    }
}
