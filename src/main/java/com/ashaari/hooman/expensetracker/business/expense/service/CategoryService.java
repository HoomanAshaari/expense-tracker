package com.ashaari.hooman.expensetracker.business.expense.service;

import com.ashaari.hooman.expensetracker.common.dto.AddCategoryRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.AddCategoryResponseDto;

public interface CategoryService {

    /**
     * Adds a new category.
     *
     * @param addCategoryRequestDto category to be added
     * @return added category
     */
    AddCategoryResponseDto addCategory(AddCategoryRequestDto addCategoryRequestDto);

}
