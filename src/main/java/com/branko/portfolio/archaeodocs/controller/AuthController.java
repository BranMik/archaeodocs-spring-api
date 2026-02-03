package com.branko.portfolio.archaeodocs.controller;

import com.branko.portfolio.archaeodocs.dto.LoginRequest;
import com.branko.portfolio.archaeodocs.dto.TokenResponse;
import com.branko.portfolio.archaeodocs.security.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtService jwt;
    private final String adminPassword;

    public AuthController(
            JwtService jwt,
            @Value("${app.admin.password}") String adminPassword
    ) {
        this.jwt = jwt;
        this.adminPassword = adminPassword;
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest req) {

        if (!adminPassword.equals(req.password())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials");
        }

        String token = jwt.createAdminToken();
        return new TokenResponse(token);
    }
}