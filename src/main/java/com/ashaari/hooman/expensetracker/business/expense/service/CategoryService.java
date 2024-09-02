package com.ashaari.hooman.expensetracker.business.expense.service;

import com.ashaari.hooman.expensetracker.common.dto.AddCategoryRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.AddCategoryResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.CategoryDto;
import com.ashaari.hooman.expensetracker.common.exception.client.ExpenseNotFoundException;

public interface CategoryService {

    /**
     * Adds a new category.
     *
     * @param addCategoryRequestDto category to be added
     * @return added category
     */
    AddCategoryResponseDto addCategory(AddCategoryRequestDto addCategoryRequestDto);

    /**
     * Finds category by ID.
     *
     * @param id category identifier
     * @return found expense, or throws not found exception
     * @throws ExpenseNotFoundException if resource doesn't exist
     */
    CategoryDto getCategory(String id);
}
