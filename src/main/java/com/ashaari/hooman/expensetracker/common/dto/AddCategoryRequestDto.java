package com.ashaari.hooman.expensetracker.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record AddCategoryRequestDto(
        @NotBlank
        String name,
        @Schema(description = "You can set a budget for a category. " +
                "Even though, its an optional field, but budgets should" +
                " be present if you want to use budgeting related services.")
        BigDecimal budget) {
}
