package com.ashaari.hooman.expensetracker.api.restcontroller;

import com.ashaari.hooman.expensetracker.aspect.Logged;
import com.ashaari.hooman.expensetracker.business.expense.service.ExpenseService;
import com.ashaari.hooman.expensetracker.common.dto.AddExpenseRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.AddExpenseResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseUpdateDto;
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
    public AddExpenseResponseDto addExpense(@RequestBody @Valid AddExpenseRequestDto addExpenseRequestDto) {
        return expenseService.addExpense(addExpenseRequestDto);
    }

    @GetMapping("/{id}")
    public ExpenseDto getExpense(@PathVariable String id) {
        return expenseService.getExpense(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeExpense(@PathVariable String id) {
        expenseService.removeExpense(id);
    }

    @PatchMapping
    public ExpenseDto updateExpense(@RequestBody @Valid ExpenseUpdateDto expenseUpdateDto) {
        return null;
    }

}
