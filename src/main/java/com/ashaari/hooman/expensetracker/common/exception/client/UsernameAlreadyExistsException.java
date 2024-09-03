package com.ashaari.hooman.expensetracker.common.exception.client;

import jakarta.validation.constraints.NotBlank;

public class UsernameAlreadyExistsException extends ExpenseTrackerClientException {

    public UsernameAlreadyExistsException(@NotBlank String username) {
        super("Username already exists: " + username);
    }

}
