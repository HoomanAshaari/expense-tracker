package com.ashaari.hooman.expensetracker.api.restcontroller;

import com.ashaari.hooman.expensetracker.aspect.Logged;
import com.ashaari.hooman.expensetracker.business.expense.service.ExpenseService;
import com.ashaari.hooman.expensetracker.common.dto.AddExpenseResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expense-tracker/api/expenses")
@RequiredArgsConstructor
@Tag(name = "Expense")
@Validated
@Logged
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Adds a new expense")
    public AddExpenseResponseDto addExpense(@RequestBody @Valid ExpenseRequestDto expenseRequestDto) {
        return expenseService.addExpense(expenseRequestDto);
    }

    @GetMapping("/{id}")
    public ExpenseResponseDto getExpense(@PathVariable String id) {
        return expenseService.getById(id);
    }

}
