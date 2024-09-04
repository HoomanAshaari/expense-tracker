package com.ashaari.hooman.expensetracker.business.expense.service.impl;

import com.ashaari.hooman.expensetracker.business.expense.mapper.ExpenseMapper;
import com.ashaari.hooman.expensetracker.business.expense.service.CategoryService;
import com.ashaari.hooman.expensetracker.business.expense.service.ExpenseService;
import com.ashaari.hooman.expensetracker.business.user.service.UserService;
import com.ashaari.hooman.expensetracker.common.dto.AddExpenseRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.AddExpenseResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseUpdateDto;
import com.ashaari.hooman.expensetracker.common.exception.client.CategoryNotFoundException;
import com.ashaari.hooman.expensetracker.common.exception.client.ExpenseNotFoundException;
import com.ashaari.hooman.expensetracker.model.expense.entity.CategoryEntity;
import com.ashaari.hooman.expensetracker.model.expense.entity.ExpenseEntity;
import com.ashaari.hooman.expensetracker.model.expense.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseMapper expenseMapper;
    private final ExpenseRepository expenseRepository;
    private final CategoryService categoryService;
    private final UserService userService;

    @Override
    @Transactional
    public AddExpenseResponseDto addExpense(AddExpenseRequestDto addExpenseRequestDto) {
        CategoryEntity categoryEntity = categoryService
                .findEntity(addExpenseRequestDto.categoryId())
                .orElseThrow(CategoryNotFoundException::new);
        ExpenseEntity expenseEntity = new ExpenseEntity();
        expenseMapper.updateEntity(expenseEntity, addExpenseRequestDto);
        String username = userService.getCurrentUsername();
        expenseEntity.setUsername(username);
        expenseEntity.setCategory(categoryEntity);
        expenseEntity = saveExpense(expenseEntity);
        return expenseMapper.toAddExpenseResponseDto(expenseEntity);
    }

    @Override
    public ExpenseEntity saveExpense(ExpenseEntity expenseEntity) {
        return expenseRepository.save(expenseEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public ExpenseDto getExpense(String id) {
        ExpenseEntity expenseEntity = findEntity(id).orElseThrow(ExpenseNotFoundException::new);
        return expenseMapper.toExpenseDto(expenseEntity);
    }

    @Override
    public Optional<ExpenseEntity> findEntity(String id) {
        // Used String for input as a best-practice,  so we could increase
        // backward-compatibility. This way we will be able to change type
        // of the ID easier later.
        if (NumberUtils.isParsable(id)) {
            return expenseRepository.findById(Long.valueOf(id));
        }
        return Optional.empty();
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
