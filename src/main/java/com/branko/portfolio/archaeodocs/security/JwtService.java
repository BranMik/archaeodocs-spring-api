package com.branko.portfolio.archaeodocs.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {

    public static final String DEFAULT_ADMIN_USER = "admin";
    public static final String ROLES = "roles";
    public static final String ADMIN = "ADMIN";

    private final SecretKey key;
    private final long expMinutes;

    public JwtService(
            @Value("${app.jwt.secret}") String base64Secret,
            @Value("${app.jwt.exp-minutes:120}") long expMinutes
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        if (keyBytes.length < 32) {
            throw new IllegalStateException("JWT secret must be at least 256 bits (32 bytes)");
        }
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.expMinutes = expMinutes;
    }

    public String createAdminToken() {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(DEFAULT_ADMIN_USER)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(expMinutes * 60)))
                .claim(ROLES, List.of(ADMIN))
                .signWith(key)   // HS256
                .compact();
    }

    public boolean isValid(String token) {
        try {
            parse(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasAdminRole(String token) {
        Claims c = parse(token).getPayload();
        Object roles = c.get(ROLES);
        return roles instanceof List<?> list && list.contains(ADMIN);
    }

    public String extractUsername(String token) {
        return parse(token).getPayload().getSubject();
    }

    private Jws<Claims> parse(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
    }
}