package com.ashaari.hooman.expensetracker.business.expense.service;

import com.ashaari.hooman.expensetracker.common.dto.AddExpenseResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseResponseDto;

public interface ExpenseService {

    /**
     * Adds a new expense.
     *
     * @param expenseRequestDto expense to be added
     * @return added expense id
     */
    AddExpenseResponseDto addExpense(ExpenseRequestDto expenseRequestDto);

    /**
     * Finds Expense by ID.
     *
     * @param id expense identifier
     * @return found expense, or throws not found exception
     * @throws {@link com.ashaari.hooman.expensetracker.common.exception.client.ExpenseNotFoundException}
     *                if resource doesn't exist
     */
    ExpenseResponseDto getById(String id);

}
