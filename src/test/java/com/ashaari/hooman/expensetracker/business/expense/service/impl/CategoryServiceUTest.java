package com.ashaari.hooman.expensetracker.business.expense.service.impl;

import com.ashaari.hooman.expensetracker.business.expense.mapper.CategoryMapper;
import com.ashaari.hooman.expensetracker.business.expense.validator.CategoryBusinessValidator;
import com.ashaari.hooman.expensetracker.business.user.service.UserService;
import com.ashaari.hooman.expensetracker.common.dto.AddCategoryRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.AddCategoryResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.CategoryDto;
import com.ashaari.hooman.expensetracker.common.exception.client.CategoryNotFoundException;
import com.ashaari.hooman.expensetracker.model.expense.entity.CategoryEntity;
import com.ashaari.hooman.expensetracker.model.expense.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceUTest {

    @Spy
    @SuppressWarnings("unused")
    private final CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);
    @Mock
    @SuppressWarnings("unused")
    private CategoryBusinessValidator categoryBusinessValidator;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private UserService userService;
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Captor
    private ArgumentCaptor<CategoryEntity> categoryEntityArgumentCaptor;

    @Test
    void addCategory_givenNewCategory_savesAndReturns() {
        // Given
        AddCategoryRequestDto transportation =
                new AddCategoryRequestDto("Transportation", BigDecimal.valueOf(1000));
        when(categoryRepository.save(any())).thenAnswer(invocationOnMock -> {
            Object arg = invocationOnMock.getArgument(0);
            CategoryEntity categoryEntity = (CategoryEntity) arg;
            categoryEntity.setId(1L);
            return categoryEntity;
        });
        given(userService.getCurrentUsername()).willReturn("current-username");
        // Act
        AddCategoryResponseDto actualResult = categoryService.addCategory(transportation);
        // Assert
        verify(categoryRepository).save(categoryEntityArgumentCaptor.capture());
        CategoryEntity actualEntityPassedToSaveMethod = categoryEntityArgumentCaptor.getValue();
        assertEquals(transportation.name(), actualEntityPassedToSaveMethod.getName());
        assertEquals(transportation.budget(), actualEntityPassedToSaveMethod.getBudget());
        assertEquals("current-username", actualEntityPassedToSaveMethod.getUsername());
        assertEquals("1", actualResult.id());
    }

    @Test
    void getCategory_givenExistingCategoryId_returnsCategory() {
        // Given
        CategoryEntity expectedCategory = CategoryEntity.builder()
                .id(1L).name("Entertainment").budget(BigDecimal.valueOf(1000)).build();
        given(userService.getCurrentUsername()).willReturn("current-username");
        given(categoryRepository.findByIdAndUsername(1L, "current-username"))
                .willReturn(Optional.of(expectedCategory));
        // Act
        CategoryDto actualCategory = categoryService.getCategory("1");
        // Assert
        verify(categoryRepository).findByIdAndUsername(1L, "current-username");
        assertEquals("1", actualCategory.id());
        assertEquals(expectedCategory.getName(), actualCategory.name());
        assertEquals(expectedCategory.getBudget(), actualCategory.budget());
    }

    @Test
    void getCategory_givenNotExistingCategoryId_throwsCategoryNotFoundException() {
        // Given
        given(userService.getCurrentUsername()).willReturn("current-username");
        given(categoryRepository.findByIdAndUsername(2L, "current-username"))
                .willReturn(Optional.empty());
        // Act, Assert
        assertThrows(CategoryNotFoundException.class, () -> categoryService.getCategory("2"));
    }

}
