package com.ashaari.hooman.expensetracker.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExpenseResponseDto(String id,
                                 BigDecimal amount,
                                 String description,
                                 String categoryId,
                                 @Schema(description = "The date on which you spent money") LocalDateTime date) {
}
