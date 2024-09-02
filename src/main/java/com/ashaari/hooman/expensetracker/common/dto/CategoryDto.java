package com.ashaari.hooman.expensetracker.common.dto;

import java.math.BigDecimal;

public record CategoryDto(
        String id,
        String name,
        BigDecimal budget) {
}
