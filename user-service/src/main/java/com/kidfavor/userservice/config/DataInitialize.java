package com.kidfavor.userservice.config;

import com.kidfavor.userservice.entity.User;
import com.kidfavor.userservice.entity.enums.Role;
import com.kidfavor.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitialize implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        initAdminUser();
    }

    private void initAdminUser() {
        String adminUsername = "admin";
        String adminPassword = "admin123";
        String adminEmail = "admin@kidfavor.com";

        // Check if admin user already exists
        if (userRepository.findByUserName(adminUsername).isEmpty()) {
            User adminUser = new User();
            adminUser.setFullName("System Administrator");
            adminUser.setUserName(adminUsername);
            adminUser.setEmail(adminEmail);
            adminUser.setPassword(passwordEncoder.encode(adminPassword));
            adminUser.setPhone("0000000000");
            adminUser.setStatus(true);
            adminUser.setRole(Role.ADMIN);

            userRepository.save(adminUser);
            log.info("========================================");
            log.info("Admin user created successfully!");
            log.info("Username: {}", adminUsername);
            log.info("Password: {}", adminPassword);
            log.info("========================================");
        } else {
            log.info("Admin user already exists, skipping initialization.");
        }
    }
}
