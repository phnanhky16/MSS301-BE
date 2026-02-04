package com.kidfavor.userservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Authentication response with tokens")
public class AuthResponse {
    
    @Schema(description = "JWT access token")
    private String accessToken;
    
    @Schema(description = "JWT refresh token")
    private String refreshToken;
    
    @Schema(description = "Token type", example = "Bearer")
    private String tokenType;
    
    @Schema(description = "Access token expiration time in seconds")
    private Long expiresIn;
    
    @Schema(description = "User information")
    private UserResponse user;
}
