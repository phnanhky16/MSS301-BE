package com.kidfavor.userservice.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Bean
    public OpenAPI userServiceOpenAPI() {
        // Gateway server should be first (default)
        Server gatewayServer = new Server();
        gatewayServer.setUrl("http://localhost:8080/user-service");
        gatewayServer.setDescription("API Gateway Server");

        Server localServer = new Server();
        localServer.setUrl("http://localhost:8081");
        localServer.setDescription("Local Development Server");

        Contact contact = new Contact();
        contact.setEmail("support@kidfavor.com");
        contact.setName("KidFavor Support Team");
        contact.setUrl("https://www.kidfavor.com");

        License license = new License()
                .name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("User Service API")
                .version("1.0.0")
                .contact(contact)
                .description("This API exposes endpoints to manage users and shipments in KidFavor system")
                .termsOfService("https://www.kidfavor.com/terms")
                .license(license);

        // Security scheme for JWT Bearer token
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization")
                .description("Enter JWT token. Get token from /auth/login API");

        SecurityRequirement securityRequirement = new SecurityRequirement().addList("Bearer Authentication");

        return new OpenAPI()
                .info(info)
                .servers(List.of(gatewayServer, localServer))
                .addSecurityItem(securityRequirement)
                .components(new Components().addSecuritySchemes("Bearer Authentication", securityScheme));
    }
}
