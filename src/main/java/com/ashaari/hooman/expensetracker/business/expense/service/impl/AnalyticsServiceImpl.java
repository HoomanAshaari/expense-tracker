package com.ashaari.hooman.expensetracker.business.expense.service.impl;

import com.ashaari.hooman.expensetracker.business.expense.service.AnalyticsService;
import com.ashaari.hooman.expensetracker.business.user.service.UserService;
import com.ashaari.hooman.expensetracker.common.dto.CategoryExpenseStatsDto;
import com.ashaari.hooman.expensetracker.model.expense.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final ExpenseRepository expenseRepository;
    private final UserService userService;

    @Override
    public CategoryExpenseStatsDto getCategoryExpenseStatistics(LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo) {
        return null;
    }
}
