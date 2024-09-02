package com.ashaari.hooman.expensetracker.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AddExpenseResponseDto(
        @Schema(description = "ID of the created expense")
        String id) {
}
