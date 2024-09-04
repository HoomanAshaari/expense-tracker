package com.ashaari.hooman.expensetracker.business.expense.service.impl;

import com.ashaari.hooman.expensetracker.business.expense.service.AnalyticsService;
import com.ashaari.hooman.expensetracker.business.user.service.UserService;
import com.ashaari.hooman.expensetracker.common.dto.CategoryExpenseStatsDto;
import com.ashaari.hooman.expensetracker.model.expense.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final ExpenseRepository expenseRepository;
    private final UserService userService;

    @Override
    public List<CategoryExpenseStatsDto> getCategoryExpenseStatistics(LocalDateTime dateTimeFrom,
                                                                      LocalDateTime dateTimeTo) {
        String username = userService.getCurrentUsername();
        return expenseRepository.findCategoryExpenseStatsByDateTimeRange(
                dateTimeFrom, dateTimeTo, username);
    }

}
