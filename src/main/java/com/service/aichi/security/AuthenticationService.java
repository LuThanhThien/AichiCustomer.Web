package com.service.aichi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.aichi.dto.LoginDto;
import com.service.aichi.dto.RegisterDto;
import com.service.aichi.entity.Token;
import com.service.aichi.entity.User;
import com.service.aichi.entity.enums.Role;
import com.service.aichi.entity.enums.TokenType;
import com.service.aichi.mapper.interfaces.IUserMapper;
import com.service.aichi.repository.interfaces.ITokenRepository;
import com.service.aichi.repository.interfaces.IUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final AuthenticationManager authManager;

    @Autowired
    private final IUserRepository userRepository;

    @Autowired
    private final ITokenRepository tokenRepository;

    public AuthenticationResponse register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return AuthenticationResponse.builder()
                    .message("User already exists!")
                    .build();
        }
        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        User user = IUserMapper.INSTANCE.registerDtoToUser(registerDto);
        user.setRole(Role.USER);
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        log.info("Extracted username: " + jwtService.extractUsername(jwtToken));
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(user, jwtToken);

        log.info("Register successfully");
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .message("Register successfully")
                .build();
    }

    public AuthenticationResponse login(LoginDto loginDto) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );
        SecurityContext securityContext = SecurityContextHolder.getContext();

        log.info("Is authenticated? (before): " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        securityContext.setAuthentication(authentication);
        log.info("Is authenticated? (after): " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        var user = userRepository.findByUsername(loginDto.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        log.info("Extracted username: " + jwtService.extractUsername(jwtToken));
        log.info("Login successfully");
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .message("Login successfully")
                .build();
    }


    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

}
