package com.ashaari.hooman.expensetracker.business.expense.service;

import com.ashaari.hooman.expensetracker.common.dto.AddExpenseRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.AddExpenseResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseDto;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Override
    public AddExpenseResponseDto addExpense(AddExpenseRequestDto addExpenseRequestDto) {
        return null;
    }

    @Override
    public ExpenseDto getExpense(String id) {
        return null;
    }

    @Override
    public void removeExpense(String id) {

    }

}
