package com.ashaari.hooman.expensetracker.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * General object, mostly used in APIs responses, for returning exceptions gracefully.
 */
public record ExceptionDto(
        @Schema(description = "Codes are unique in this system, so " +
                "you can make programming decisions based on them.")
        String errorCode,
        @Schema(description = "Some more info on what when wrong")
        String errorMessage) {
}
