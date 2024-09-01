package com.ashaari.hooman.expensetracker.common.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExpenseResponseDto(
        String id,
        BigDecimal amount,
        String description,
        String categoryId,
        LocalDateTime date) {
}
