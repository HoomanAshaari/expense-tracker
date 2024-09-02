package com.ashaari.hooman.expensetracker.business.expense.service;

import com.ashaari.hooman.expensetracker.common.dto.AddCategoryRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.AddCategoryResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.CategoryDto;
import com.ashaari.hooman.expensetracker.common.exception.client.ExpenseNotFoundException;
import com.ashaari.hooman.expensetracker.model.expense.entity.CategoryEntity;

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

    /**
     * Saves or updates a category.
     *
     * @param categoryEntity category to be saved/updated.
     * @return saved category
     */
    CategoryEntity saveCategory(CategoryEntity categoryEntity);

}
