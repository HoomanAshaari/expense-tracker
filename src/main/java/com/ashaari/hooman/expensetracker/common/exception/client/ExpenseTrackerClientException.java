package com.ashaari.hooman.expensetracker.common.exception.client;

import jakarta.validation.constraints.NotEmpty;

/**
 * Base exception class for  'Expense Tracker System',
 * client related exceptions, usually caused based on
 * business rules.
 */
public class ExpenseTrackerClientException extends RuntimeException {

    public ExpenseTrackerClientException() {}

    public ExpenseTrackerClientException(@NotEmpty String name) {
        super(name);
    }

}
