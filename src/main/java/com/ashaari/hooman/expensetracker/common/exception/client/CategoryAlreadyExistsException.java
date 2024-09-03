package com.ashaari.hooman.expensetracker.common.exception.client;

import jakarta.validation.constraints.NotEmpty;

public class CategoryAlreadyExistsException extends ExpenseTrackerClientException {

    public CategoryAlreadyExistsException(@NotEmpty String name) {
        super("Existing category: " + name);
    }

}
