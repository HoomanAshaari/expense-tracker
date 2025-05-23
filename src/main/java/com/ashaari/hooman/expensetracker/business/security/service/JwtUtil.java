package com.ashaari.hooman.expensetracker.business.security.service;

import jakarta.validation.constraints.NotBlank;
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
     * Checks whether is expired or not
     *
     * @param token to be checked
     * @return true if expiration time has passed, otherwise false
     */
    boolean isTokenExpired(String token);

    /**
     * Extracts username from given token
     *
     * @param token from which username should get extracted
     */
    String extractUsername(String token);

    /**
     * Generates a token based on given username
     *
     * @param username to be used for token generation
     * @return generated token
     */
    String generateToken(@NotBlank String username);

}
