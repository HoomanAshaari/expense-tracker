package com.ashaari.hooman.expensetracker.business.expense.service;

import com.ashaari.hooman.expensetracker.common.dto.ExpenseRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseResponseDto;

public interface ExpenseService {

    ExpenseResponseDto addExpense(ExpenseRequestDto expenseRequestDto);

}
