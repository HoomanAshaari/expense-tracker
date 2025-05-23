package com.ashaari.hooman.expensetracker.business.security.service.impl;

import com.ashaari.hooman.expensetracker.business.security.service.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtilImpl implements JwtUtil {

    @Value("${expense-tracker.jwt.secret}")
    private String secretKey;
    @Value("${expense-tracker.jwt.expiration-minutes}")
    private int expirationMinutes;

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    @Override
    public boolean isTokenExpired(String token) {
        Claims claims = getTokenClaims(token);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    @Override
    public String extractUsername(String token) {
        Claims claims = getTokenClaims(token);
        return claims.getSubject();
    }

    @Override
    public String generateToken(String username) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(username)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(expirationMinutes, ChronoUnit.MINUTES)))
                .signWith(getSecretKey())
                .compact();
    }

    private Claims getTokenClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    private SecretKey getSecretKey() {
        byte[] secretBytes = Base64.getDecoder().decode(secretKey.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(secretBytes, "HmacSHA256");
    }

}
