package com.service.aichi.controller;

import com.service.aichi.dto.LoginDto;
import com.service.aichi.dto.RegisterDto;
import com.service.aichi.security.AuthenticationResponse;
import com.service.aichi.security.AuthenticationService;
import com.service.aichi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auth")
@Slf4j
public class AuthenticationController {

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final AuthenticationService authService;

    @GetMapping("/register")
    public ResponseEntity<String> registerForm() {
        return ResponseEntity.ok("On register page");
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterDto registerDto) {
        return ResponseEntity.ok(authService.register(registerDto));
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginPage() {
        return ResponseEntity.ok("On login page");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }


}
