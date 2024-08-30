package com.ashaari.hooman.expensetracker.api.restcontroller;

import com.ashaari.hooman.expensetracker.common.dto.ExpenseRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expense-tracker/api/expenses")
@Tag(name = "Expense")
@Validated
public class ExpenseController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Adds a new expense")
    public ExpenseResponseDto addExpense(@RequestBody @Valid ExpenseRequestDto expenseRequestDto) {
        return null;
    }

}
