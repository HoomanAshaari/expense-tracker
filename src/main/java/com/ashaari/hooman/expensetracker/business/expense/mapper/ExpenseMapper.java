package com.ashaari.hooman.expensetracker.business.expense.mapper;

import com.ashaari.hooman.expensetracker.common.dto.AddExpenseRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.AddExpenseResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.ExpenseDto;
import com.ashaari.hooman.expensetracker.model.expense.entity.CategoryEntity;
import com.ashaari.hooman.expensetracker.model.expense.entity.ExpenseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    void updateEntity(@MappingTarget ExpenseEntity expenseEntity,
                               AddExpenseRequestDto addExpenseRequestDto);

    AddExpenseResponseDto toAddExpenseResponseDto(ExpenseEntity expenseEntity);

    @Mapping(source = "category", target = "categoryId", qualifiedByName = "longToString")
    ExpenseDto toExpenseDto(ExpenseEntity expenseEntity);

    @Named("longToString")
    @SuppressWarnings("unused")
    default String longToString(CategoryEntity categoryEntity) {
        return categoryEntity.getId().toString();
    }

}
