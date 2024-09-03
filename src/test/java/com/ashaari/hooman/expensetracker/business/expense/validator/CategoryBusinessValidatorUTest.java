package com.ashaari.hooman.expensetracker.business.expense.validator;

import com.ashaari.hooman.expensetracker.common.dto.AddCategoryRequestDto;
import com.ashaari.hooman.expensetracker.common.exception.client.CategoryAlreadyExistsException;
import com.ashaari.hooman.expensetracker.model.expense.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CategoryBusinessValidatorUTest {

    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryBusinessValidator validator;

    @Test
    void validateCategoryExistence_givenExistingCategoryName_throwsCategoryAlreadyExistsException() {
        // Given
        given(categoryRepository.existsByName("Entertainment")).willReturn(true);
        // Act, Assert
        assertThrows(CategoryAlreadyExistsException.class, () -> validator.validateCategoryExistence(
                new AddCategoryRequestDto("Entertainment", BigDecimal.valueOf(1000))));
    }

}
