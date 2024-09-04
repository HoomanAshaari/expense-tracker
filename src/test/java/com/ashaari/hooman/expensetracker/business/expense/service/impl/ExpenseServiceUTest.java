package com.ashaari.hooman.expensetracker.business.expense.service.impl;

import com.ashaari.hooman.expensetracker.business.expense.mapper.ExpenseMapper;
import com.ashaari.hooman.expensetracker.business.expense.service.CategoryService;
import com.ashaari.hooman.expensetracker.business.user.service.UserService;
import com.ashaari.hooman.expensetracker.common.dto.AddExpenseRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.AddExpenseResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseDto;
import com.ashaari.hooman.expensetracker.common.exception.client.CategoryNotFoundException;
import com.ashaari.hooman.expensetracker.common.exception.client.ExpenseNotFoundException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @Mock
    private UserService userService;
    @InjectMocks
    private ExpenseServiceImpl expenseService;

    @Captor
    private ArgumentCaptor<ExpenseEntity> expenseEntityArgumentCaptor;

    @Test
    void addExpense_givenNewExpense_addsExpenseAndReturnPersistedId() {
        // Given
        AddExpenseRequestDto hadAnIceCream = new AddExpenseRequestDto(
                BigDecimal.ONE, "Had an ice cream", "5", LocalDateTime.now());
        given(userService.getCurrentUsername()).willReturn("current-username");
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
        assertEquals("1", actualResult.id());
        assertEquals(5, actualEntityPassedToSaveMethod.getCategory().getId());
        assertEquals(hadAnIceCream.amount(), actualEntityPassedToSaveMethod.getAmount());
        assertEquals(hadAnIceCream.description(), actualEntityPassedToSaveMethod.getDescription());
        assertEquals("current-username", actualEntityPassedToSaveMethod.getUsername());
    }

    @Test
    void addExpense_givenNonExistingCategoryInRequest_throwsCategoryNotFoundException() {
        // Given
        AddExpenseRequestDto hadAnIceCream = new AddExpenseRequestDto(
                BigDecimal.ONE, "Had an ice cream", "50", LocalDateTime.now());
        given(categoryService.findEntity("50")).willReturn(Optional.empty());
        // Act, Assert
        assertThrows(CategoryNotFoundException.class, () -> expenseService.addExpense(hadAnIceCream));
        verify(categoryService).findEntity("50");
    }

    @Test
    void getExpense_givenExistingExpense_returnsExpense() {
        // Given
        CategoryEntity category = new CategoryEntity();
        category.setId(5L);
        ExpenseEntity moviesTicket = ExpenseEntity.builder()
                .id(1L)
                .amount(BigDecimal.TEN)
                .category(category)
                .description("Movies ticket")
                .spentOn(LocalDateTime.now()).build();
        given(expenseRepository.findById(1L)).willReturn(Optional.of(moviesTicket));
        // Act
        ExpenseDto actualExpense = expenseService.getExpense("1");
        // Assert
        assertEquals("1", actualExpense.id());
        assertEquals("5", actualExpense.categoryId());
        assertEquals(moviesTicket.getAmount(), actualExpense.amount());
        assertEquals(moviesTicket.getDescription(), actualExpense.description());
        assertEquals(moviesTicket.getSpentOn(), actualExpense.spentOn());
    }

    @Test
    void getExpense_givenNonExistingExpense_throwsExpenseNotFoundException() {
        // Given
        given(expenseRepository.findById(2L)).willReturn(Optional.empty());
        // Act, Assert
        assertThrows(ExpenseNotFoundException.class, () -> expenseService.getExpense("2"));
    }

}
