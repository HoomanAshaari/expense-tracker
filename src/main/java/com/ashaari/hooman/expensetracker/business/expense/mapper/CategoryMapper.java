package com.ashaari.hooman.expensetracker.business.expense.mapper;

import com.ashaari.hooman.expensetracker.common.dto.AddCategoryRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.AddCategoryResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.CategoryDto;
import com.ashaari.hooman.expensetracker.model.expense.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    void updateEntity(@MappingTarget CategoryEntity category,
                      AddCategoryRequestDto addCategoryRequestDto);

    AddCategoryResponseDto toAddCategoryResponseDto(CategoryEntity categoryEntity);

    CategoryDto toCategoryDto(CategoryEntity categoryEntity);

}
