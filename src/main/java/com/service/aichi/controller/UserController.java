package com.service.aichi.controller;

import com.service.aichi.dto.LoginDto;
import com.service.aichi.dto.RegisterDto;
import com.service.aichi.entity.User;
import com.service.aichi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/home")
    public ResponseEntity<String> userHome() {
        return ResponseEntity.ok("Welcome home");
    }



}
