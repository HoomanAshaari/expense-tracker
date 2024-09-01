package com.ashaari.hooman.expensetracker.business.expense.service;

import com.ashaari.hooman.expensetracker.common.dto.AddExpenseResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseResponseDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Override
    public AddExpenseResponseDto addExpense(ExpenseRequestDto expenseRequestDto) {
        return null;
    }

    @Override
    public Optional<ExpenseResponseDto> findExpense(String id) {
        return Optional.empty();
    }

}
