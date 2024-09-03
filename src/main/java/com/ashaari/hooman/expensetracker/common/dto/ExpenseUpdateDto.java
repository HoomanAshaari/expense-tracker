package com.ashaari.hooman.expensetracker.common.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ExpenseUpdateDto(
        @NotBlank
        String id,
        @DecimalMin(value = "0.0", inclusive = false)
        BigDecimal amount,
        String description,
        String categoryId) {
}
