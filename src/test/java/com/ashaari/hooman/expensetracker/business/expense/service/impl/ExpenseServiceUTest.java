package com.ashaari.hooman.expensetracker.business.expense.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceUTest {

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    @Test
    void addExpense_givenNewExpense_addsExpenseAndReturnPersistedId() {
        // Given
        // Act
        // Assert
    }

}
