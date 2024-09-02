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

import java.util.Optional;

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
    public CategoryEntity saveCategory(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public CategoryDto getCategory(String id) {
        return null;
    }

    @Override
    public Optional<CategoryEntity> findEntity(String id) {
        // Used String for input as a best-practice,  so we could increase
        // backward-compatibility. This way we will be able to change type
        // of the ID easier later.
        long longId;
        try {
            longId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
        return categoryRepository.findById(longId);
    }

}
