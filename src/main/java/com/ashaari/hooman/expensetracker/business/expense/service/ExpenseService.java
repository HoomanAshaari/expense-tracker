package com.ashaari.hooman.expensetracker.business.expense.service;

import com.ashaari.hooman.expensetracker.common.dto.AddExpenseRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.AddExpenseResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseUpdateDto;
import com.ashaari.hooman.expensetracker.common.exception.client.ExpenseNotFoundException;
import com.ashaari.hooman.expensetracker.model.expense.entity.ExpenseEntity;
import jakarta.validation.Valid;

public interface ExpenseService {

    /**
     * Adds a new expense.
     *
     * @param addExpenseRequestDto expense to be added
     * @return added expense id
     */
    AddExpenseResponseDto addExpense(AddExpenseRequestDto addExpenseRequestDto);

    /**
     * Saves or updates a expense.
     *
     * @param expenseEntity expense to be saved/updated
     * @return saved expense
     */
    ExpenseEntity saveExpense(ExpenseEntity expenseEntity);

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

    /**
     * Updates given expense based on new content (if available).
     * So you should set fields that you want to update them with
     * new values.
     *
     * @param expenseUpdateDto fields to be updated
     * @return updated expense
     */
    ExpenseDto partialUpdate(@Valid ExpenseUpdateDto expenseUpdateDto);
}
