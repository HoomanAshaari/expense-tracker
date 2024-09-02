package com.ashaari.hooman.expensetracker.business.expense.service.impl;

import com.ashaari.hooman.expensetracker.business.expense.mapper.CategoryMapper;
import com.ashaari.hooman.expensetracker.business.expense.service.CategoryService;
import com.ashaari.hooman.expensetracker.common.dto.AddCategoryRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.AddCategoryResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.CategoryDto;
import com.ashaari.hooman.expensetracker.model.expense.entity.CategoryEntity;
import com.ashaari.hooman.expensetracker.model.expense.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public AddCategoryResponseDto addCategory(AddCategoryRequestDto addCategoryRequestDto) {
        CategoryEntity categoryEntity = categoryMapper.toEntity(addCategoryRequestDto);
        categoryEntity = saveCategory(categoryEntity);
        return categoryMapper.toAddCategoryResponseDto(categoryEntity);
    }

    @Override
    public CategoryDto getCategory(String id) {
        return null;
    }

    @Override
    public CategoryEntity saveCategory(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

}
