package com.ashaari.hooman.expensetracker.business.expense.service.impl;

import com.ashaari.hooman.expensetracker.business.expense.mapper.ExpenseMapper;
import com.ashaari.hooman.expensetracker.business.expense.service.CategoryService;
import com.ashaari.hooman.expensetracker.common.dto.AddExpenseRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.AddExpenseResponseDto;
import com.ashaari.hooman.expensetracker.model.expense.entity.CategoryEntity;
import com.ashaari.hooman.expensetracker.model.expense.entity.ExpenseEntity;
import com.ashaari.hooman.expensetracker.model.expense.repository.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceUTest {

    @Spy
    @SuppressWarnings("unused")
    private ExpenseMapper expenseMapper = Mappers.getMapper(ExpenseMapper.class);
    @Mock
    private CategoryService categoryService;
    @Mock
    private ExpenseRepository expenseRepository;
    @InjectMocks
    private ExpenseServiceImpl expenseService;

    @Captor
    private ArgumentCaptor<ExpenseEntity> expenseEntityArgumentCaptor;

    @Test
    void addExpense_givenNewExpense_addsExpenseAndReturnPersistedId() {
        // Given
        AddExpenseRequestDto hadAnIceCream = new AddExpenseRequestDto(
                BigDecimal.ONE, "Had an ice cream", "5", LocalDateTime.now());
        when(expenseRepository.save(any())).thenAnswer(invocationOnMock -> {
            invocationOnMock.getArgument(0);
            Object arg = invocationOnMock.getArgument(0);
            ExpenseEntity expenseEntity = (ExpenseEntity) arg;
            expenseEntity.setId(1L);
            return expenseEntity;
        });
        CategoryEntity funCategory = CategoryEntity.builder()
                .id(5L).name("fun").budget(BigDecimal.valueOf(100)).build();
        given(categoryService.findEntity("5")).willReturn(Optional.of(funCategory));
        // Act
        AddExpenseResponseDto actualResult = expenseService.addExpense(hadAnIceCream);
        // Assert
        verify(expenseRepository).save(expenseEntityArgumentCaptor.capture());
        ExpenseEntity actualEntityPassedToSaveMethod = expenseEntityArgumentCaptor.getValue();
        assertEquals(1L, actualEntityPassedToSaveMethod.getId());
        assertEquals(Long.valueOf(hadAnIceCream.categoryId()), actualEntityPassedToSaveMethod.getCategory().getId());
        assertEquals(hadAnIceCream.amount(), actualEntityPassedToSaveMethod.getAmount());
        assertEquals(hadAnIceCream.description(), actualEntityPassedToSaveMethod.getDescription());
    }

}
