package com.ashaari.hooman.expensetracker.business.expense.service.impl;

import com.ashaari.hooman.expensetracker.business.expense.mapper.CategoryMapper;
import com.ashaari.hooman.expensetracker.business.expense.service.CategoryService;
import com.ashaari.hooman.expensetracker.business.expense.validator.CategoryBusinessValidator;
import com.ashaari.hooman.expensetracker.business.user.service.UserService;
import com.ashaari.hooman.expensetracker.common.dto.AddCategoryRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.AddCategoryResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.CategoryDto;
import com.ashaari.hooman.expensetracker.common.exception.client.CategoryNotFoundException;
import com.ashaari.hooman.expensetracker.model.expense.entity.CategoryEntity;
import com.ashaari.hooman.expensetracker.model.expense.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryBusinessValidator categoryBusinessValidator;
    private final UserService userService;

    @Override
    @Transactional
    public AddCategoryResponseDto addCategory(AddCategoryRequestDto addCategoryRequestDto) {
        categoryBusinessValidator.validate(addCategoryRequestDto);
        CategoryEntity categoryEntity = new CategoryEntity();
        String username = userService.getCurrentUsername();
        categoryEntity.setUsername(username);
        categoryMapper.updateEntity(categoryEntity, addCategoryRequestDto);
        categoryEntity = saveCategory(categoryEntity);
        return categoryMapper.toAddCategoryResponseDto(categoryEntity);
    }

    @Override
    public CategoryEntity saveCategory(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto getCategory(String id) {
        CategoryEntity categoryEntity = findEntity(id).orElseThrow(CategoryNotFoundException::new);
        return categoryMapper.toCategoryDto(categoryEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CategoryEntity> findEntity(String id) {
        // Used String for input as a best-practice,  so we could increase
        // backward-compatibility. This way we will be able to change type
        // of the ID easier later.
        if (NumberUtils.isParsable(id)) {
            return categoryRepository.findByIdAndUsername(Long.valueOf(id), userService.getCurrentUsername());
        }
        return Optional.empty();
    }

}
