package com.kidfavor.userservice.dto.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Login request payload")
public class LoginRequest {
    
    @NotBlank(message = "Username is required")
    @Schema(description = "Username for login", example = "admin")
    private String username;
    
    @NotBlank(message = "Password is required")
    @Schema(description = "Password for login", example = "admin123")
    private String password;
}
