package com.ashaari.hooman.expensetracker.business.security.service;

public interface JwtUtil {

    /**
     * Extracts username from given token
     * 
     * @param token from which username should get extracted
     */
    String extractUsername(String token);

}
