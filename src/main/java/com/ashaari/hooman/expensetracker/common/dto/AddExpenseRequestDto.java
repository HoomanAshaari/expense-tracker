package com.ashaari.hooman.expensetracker.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AddExpenseRequestDto(
        @DecimalMin(value = "0.0", inclusive = false)
        BigDecimal amount,
        String description,
        @NotEmpty
        String categoryId,
        @Schema(description = "The date on which you spent money. " +
                "(Current date/time will be used if not specified.)")
        LocalDateTime spentOn) {
}
