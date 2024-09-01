package com.ashaari.hooman.expensetracker.business.expense.service;

import com.ashaari.hooman.expensetracker.common.dto.AddExpenseResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseResponseDto;
import com.ashaari.hooman.expensetracker.common.exception.client.ExpenseNotFoundException;

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
     * @throws ExpenseNotFoundException if resource doesn't exist
     */
    ExpenseResponseDto getExpense(String id);

}
