package com.ashaari.hooman.expensetracker.model.expense.repository;

import com.ashaari.hooman.expensetracker.model.expense.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    boolean existsByName(String name);

}
