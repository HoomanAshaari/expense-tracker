package com.ashaari.hooman.expensetracker.business.expense.service.impl;

import com.ashaari.hooman.expensetracker.business.expense.mapper.ExpenseMapper;
import com.ashaari.hooman.expensetracker.business.expense.service.CategoryService;
import com.ashaari.hooman.expensetracker.business.expense.service.ExpenseService;
import com.ashaari.hooman.expensetracker.common.dto.AddExpenseRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.AddExpenseResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseUpdateDto;
import com.ashaari.hooman.expensetracker.common.exception.client.CategoryNotFoundException;
import com.ashaari.hooman.expensetracker.model.expense.entity.CategoryEntity;
import com.ashaari.hooman.expensetracker.model.expense.entity.ExpenseEntity;
import com.ashaari.hooman.expensetracker.model.expense.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseMapper expenseMapper;
    private final ExpenseRepository expenseRepository;
    private final CategoryService categoryService;

    @Override
    @Transactional
    public AddExpenseResponseDto addExpense(AddExpenseRequestDto addExpenseRequestDto) {
        CategoryEntity categoryEntity = categoryService
                .findEntity(addExpenseRequestDto.categoryId())
                .orElseThrow(CategoryNotFoundException::new);
        ExpenseEntity expenseEntity = expenseMapper.toExpenseEntity(addExpenseRequestDto);
        expenseEntity.setCategory(categoryEntity);
        expenseEntity = expenseRepository.save(expenseEntity);
        return expenseMapper.toAddExpenseResponseDto(expenseEntity);
    }

    @Override
    public ExpenseEntity saveExpense(ExpenseEntity expenseEntity) {
        return expenseRepository.save(expenseEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public ExpenseDto getExpense(String id) {
        return null;
    }

    @Override
    public Optional<ExpenseEntity> findEntity(String id) {
        return expenseRepository.findById(Long.valueOf(id));
    }

    @Override
    public void removeExpense(String id) {

    }

    @Override
    @Transactional
    public ExpenseDto partialUpdate(ExpenseUpdateDto expenseUpdateDto) {
        return null;
    }

}
