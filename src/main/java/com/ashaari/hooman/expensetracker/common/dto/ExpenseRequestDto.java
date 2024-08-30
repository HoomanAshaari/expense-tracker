package com.ashaari.hooman.expensetracker.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record ExpenseRequestDto(@NotNull BigDecimal amount,
                                String description,
                                @NotEmpty String categoryId,
                                @Schema(description = "The date on which you spent money. " +
                                        "(Current date/time will be used if not specified.)")
                                ZonedDateTime date) {
}
