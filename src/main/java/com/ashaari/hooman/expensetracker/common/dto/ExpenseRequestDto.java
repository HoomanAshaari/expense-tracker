package com.ashaari.hooman.expensetracker.common.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record ExpenseRequestDto(BigDecimal amount,
                                String description,
                                String categoryId,
                                ZonedDateTime date) {
}
