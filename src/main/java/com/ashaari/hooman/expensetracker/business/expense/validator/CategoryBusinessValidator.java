package com.ashaari.hooman.expensetracker.business.expense.validator;

import com.ashaari.hooman.expensetracker.common.dto.AddCategoryRequestDto;
import com.ashaari.hooman.expensetracker.common.exception.client.CategoryAlreadyExistsException;
import com.ashaari.hooman.expensetracker.model.expense.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryBusinessValidator {

    private final CategoryRepository categoryRepository;

    public void validate(AddCategoryRequestDto addCategoryRequestDto) {
        validateCategoryExistence(addCategoryRequestDto);
    }

    public void validateCategoryExistence(AddCategoryRequestDto addCategoryRequestDto) {
        if (categoryRepository.existsByName(addCategoryRequestDto.name())) {
            throw new CategoryAlreadyExistsException(addCategoryRequestDto.name());
        }
    }

}
