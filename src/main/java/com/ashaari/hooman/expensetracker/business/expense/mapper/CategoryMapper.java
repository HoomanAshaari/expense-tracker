package com.ashaari.hooman.expensetracker.business.expense.mapper;

import com.ashaari.hooman.expensetracker.common.dto.AddCategoryRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.AddCategoryResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.CategoryDto;
import com.ashaari.hooman.expensetracker.model.expense.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryEntity toEntity(AddCategoryRequestDto addCategoryRequestDto);

    AddCategoryResponseDto toAddCategoryResponseDto(CategoryEntity categoryEntity);

    CategoryDto toCategoryDto(CategoryEntity categoryEntity);

}
