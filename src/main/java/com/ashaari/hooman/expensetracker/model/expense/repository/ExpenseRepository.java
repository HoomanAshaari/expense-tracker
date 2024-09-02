package com.ashaari.hooman.expensetracker.model.expense.repository;

import com.ashaari.hooman.expensetracker.model.expense.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {
}
