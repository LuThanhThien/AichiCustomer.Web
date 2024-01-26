package com.service.aichi.utility;

import com.service.aichi.entity.enums.Role;
import com.service.aichi.entity.User;
import com.service.aichi.security.JwtService;
import com.service.aichi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    @Autowired
    private final UserService userService;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public void run(String... args) throws Exception {

        if(userService.count() == 0) {

            User newAdmin = User.builder()
                    .username("admin@aichi.vn")
                    .password(passwordEncoder.encode("qwer1234@"))
                    .firstName("Admin")
                    .lastName("Aichi")
                    .role(Role.ADMIN)
                    .build();
            userService.save(newAdmin);
            String adminToken = jwtService.generateToken(newAdmin);
            log.info("Admin token: " + adminToken);

            User newUser = User.builder()
                    .username("user@aichi.vn")
                    .password(passwordEncoder.encode("qwer1234@"))
                    .firstName("User")
                    .lastName("Aichi")
                    .role(Role.USER)
                    .build();
            userService.save(newUser);
            String userToken = jwtService.generateToken(newUser);
            log.info("User token: " + userToken);

        }
    }
}

