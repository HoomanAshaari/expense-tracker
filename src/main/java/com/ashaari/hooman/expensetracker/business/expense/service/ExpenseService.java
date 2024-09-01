package com.ashaari.hooman.expensetracker.business.expense.service;

import com.ashaari.hooman.expensetracker.common.dto.AddExpenseRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.AddExpenseResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseDto;
import com.ashaari.hooman.expensetracker.common.exception.client.ExpenseNotFoundException;

public interface ExpenseService {

    /**
     * Adds a new expense.
     *
     * @param addExpenseRequestDto expense to be added
     * @return added expense id
     */
    AddExpenseResponseDto addExpense(AddExpenseRequestDto addExpenseRequestDto);

    /**
     * Finds expense by ID.
     *
     * @param id expense identifier
     * @return found expense, or throws not found exception
     * @throws ExpenseNotFoundException if resource doesn't exist
     */
    ExpenseDto getExpense(String id);

    /**
     * Removes expense by ID.
     *
     * @param id expense to be deleted
     */
    void removeExpense(String id);
}
