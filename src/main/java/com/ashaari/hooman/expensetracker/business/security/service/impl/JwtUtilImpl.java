package com.ashaari.hooman.expensetracker.business.security.service.impl;

import com.ashaari.hooman.expensetracker.business.security.service.JwtUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtilImpl implements JwtUtil {

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        return false;
    }

    @Override
    public String extractUsername(String token) {
        return "";
    }

}
