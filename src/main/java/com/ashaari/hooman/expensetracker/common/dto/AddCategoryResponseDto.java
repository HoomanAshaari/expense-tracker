package com.ashaari.hooman.expensetracker.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AddCategoryResponseDto(
        @Schema(description = "ID of the created category")
        String id) {
}
