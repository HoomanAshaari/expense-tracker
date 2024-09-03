package com.ashaari.hooman.expensetracker.common.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequestDto(
        @NotBlank
        String username,
        @NotBlank @Size(min = 8, max = 32)
        String password,
        @NotBlank
        @Email
        String email) {
}
