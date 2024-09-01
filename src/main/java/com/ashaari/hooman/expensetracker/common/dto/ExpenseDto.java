package com.ashaari.hooman.expensetracker.common.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExpenseDto(
        String id,
        BigDecimal amount,
        String description,
        String categoryId,
        LocalDateTime date) {
}
