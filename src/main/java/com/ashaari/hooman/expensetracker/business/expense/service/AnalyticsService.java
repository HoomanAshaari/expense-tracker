package com.ashaari.hooman.expensetracker.business.expense.service;

import com.ashaari.hooman.expensetracker.common.dto.CategoryExpenseStatsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface AnalyticsService {

    /**
     * Returns statistics about money spent in each category, within the given date range.
     */
    List<CategoryExpenseStatsDto> getCategoryExpenseStatistics(LocalDateTime dateFrom, LocalDateTime dateTimeTo);

}
