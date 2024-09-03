package com.ashaari.hooman.expensetracker.business.security.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtUtil {

    /**
     * Validates username and expiration of token
     *
     * @param token       to be validated
     * @param userDetails information to be used  for  validation
     * @return true if userDetails username is valid  and   token
     * is not expired yet, false if one of these validations fail
     */
    boolean validateToken(String token, UserDetails userDetails);

    /**
     * Extracts username from given token
     *
     * @param token from which username should get extracted
     */
    String extractUsername(String token);

}
