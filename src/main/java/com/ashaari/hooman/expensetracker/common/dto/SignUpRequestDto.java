package com.ashaari.hooman.expensetracker.common.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequestDto(
        @NotBlank
        String username,
        @NotBlank
        @Size(min = 8, max = 32)
        String password,
        @Email
        @NotBlank
        String email) {
}
