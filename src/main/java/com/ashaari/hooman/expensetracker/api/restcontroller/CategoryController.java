package com.ashaari.hooman.expensetracker.api.restcontroller;

import com.ashaari.hooman.expensetracker.aspect.Logged;
import com.ashaari.hooman.expensetracker.business.expense.service.CategoryService;
import com.ashaari.hooman.expensetracker.common.dto.AddCategoryRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.AddCategoryResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.CategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ashaari.hooman.expensetracker.api.restcontroller.util.RestUtils.CATEGORIES_API_V_1;

@RestController
@RequestMapping(CATEGORIES_API_V_1)
@RequiredArgsConstructor
@Tag(name = "Category")
@Validated
@Logged
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Adds a new category")
    public AddCategoryResponseDto addCategory(@RequestBody @Valid AddCategoryRequestDto addCategoryRequestDto) {
        return categoryService.addCategory(addCategoryRequestDto);
    }

    @GetMapping("/{id}")
    @Operation(description = "Finds a category by given ID. Not found " +
            "error will be returned if there is no resource for the given ID.")
    public CategoryDto getCategory(@PathVariable String id) {
        return categoryService.getCategory(id);
    }

    // Category removal and modification, needs more considerations,
    // so we could postpone their implementation to next the  phase.

}
