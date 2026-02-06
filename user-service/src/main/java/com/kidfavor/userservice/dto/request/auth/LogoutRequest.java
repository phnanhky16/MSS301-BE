package com.kidfavor.userservice.dto.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Logout request")
public class LogoutRequest {

    @NotBlank(message = "Token is required")
    @Schema(description = "JWT access token to invalidate", example = "eyJhbGciOiJIUzUxMiJ9...")
    private String token;
}
