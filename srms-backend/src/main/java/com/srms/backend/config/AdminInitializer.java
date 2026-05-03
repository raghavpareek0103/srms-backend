package com.srms.backend.config;

import com.srms.backend.entity.User;
import com.srms.backend.repository.UserRepository;
import com.srms.backend.security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AdminInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner createAdmin() {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("Admin@123"))
                        .role(Role.ADMIN)
                        .build();
                userRepository.save(admin);
                System.out.println("✅ ADMIN CREATED");
            } else {
                System.out.println("ℹ️ Admin already exists");
            }
        };
    }
}