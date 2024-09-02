package com.ashaari.hooman.expensetracker.business.expense.service.impl;

import com.ashaari.hooman.expensetracker.business.expense.service.CategoryService;
import com.ashaari.hooman.expensetracker.business.expense.service.ExpenseService;
import com.ashaari.hooman.expensetracker.common.dto.AddExpenseRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.AddExpenseResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseUpdateDto;
import com.ashaari.hooman.expensetracker.model.expense.entity.ExpenseEntity;
import com.ashaari.hooman.expensetracker.model.expense.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryService categoryService;

    @Override
    public AddExpenseResponseDto addExpense(AddExpenseRequestDto addExpenseRequestDto) {
        return null;
    }

    @Override
    public ExpenseEntity saveExpense(ExpenseEntity expenseEntity) {
        return expenseRepository.save(expenseEntity);
    }

    @Override
    public ExpenseDto getExpense(String id) {
        return null;
    }

    @Override
    public void removeExpense(String id) {

    }

    @Override
    public ExpenseDto partialUpdate(ExpenseUpdateDto expenseUpdateDto) {
        return null;
    }

}
