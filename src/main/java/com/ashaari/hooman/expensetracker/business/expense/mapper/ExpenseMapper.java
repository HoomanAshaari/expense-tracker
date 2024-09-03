package com.ashaari.hooman.expensetracker.business.expense.mapper;

import com.ashaari.hooman.expensetracker.common.dto.AddExpenseRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.AddExpenseResponseDto;
import com.ashaari.hooman.expensetracker.model.expense.entity.ExpenseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    ExpenseEntity toExpenseEntity(AddExpenseRequestDto addExpenseRequestDto);

    AddExpenseResponseDto toAddExpenseResponseDto(ExpenseEntity expenseEntity);

}
