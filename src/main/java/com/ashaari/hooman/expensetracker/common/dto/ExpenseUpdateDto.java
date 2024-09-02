package com.ashaari.hooman.expensetracker.common.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public record ExpenseUpdateDto(
        @NotEmpty
        String id,
        @DecimalMin(value = "0.0", inclusive = false)
        BigDecimal amount,
        String description,
        String categoryId) {
}
