package com.ashaari.hooman.expensetracker.business.security.service.impl;

import com.ashaari.hooman.expensetracker.business.security.service.JwtUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtilImpl implements JwtUtil {

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    @Override
    public boolean isTokenExpired(String token) {
        return true;
    }

    @Override
    public String extractUsername(String token) {
        return "";
    }

}
