package com.ashaari.hooman.expensetracker.api.restcontroller;

import com.ashaari.hooman.expensetracker.aspect.Logged;
import com.ashaari.hooman.expensetracker.common.dto.AddCategoryRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.AddCategoryResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expense-tracker/api/v1/categories")
@Tag(name = "Category")
@Validated
@Logged
public class CategoryController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Adds a new category")
    public AddCategoryResponseDto addCategory(@RequestBody @Valid AddCategoryRequestDto addCategoryRequestDto) {
        return null;
    }

}
