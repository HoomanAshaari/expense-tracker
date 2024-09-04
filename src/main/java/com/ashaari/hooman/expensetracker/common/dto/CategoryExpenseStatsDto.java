package com.ashaari.hooman.expensetracker.common.dto;

import java.math.BigDecimal;

public record CategoryExpenseStatsDto(
        String categoryName,
        BigDecimal totalAmount,
        BigDecimal budget) {
}
