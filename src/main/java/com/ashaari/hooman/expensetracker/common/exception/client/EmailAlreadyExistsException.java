package com.ashaari.hooman.expensetracker.common.exception.client;

import jakarta.validation.constraints.NotBlank;

public class EmailAlreadyExistsException extends ExpenseTrackerClientException {

    public EmailAlreadyExistsException(@NotBlank String username) {
        super("Email already exists: " + username);
    }

}
