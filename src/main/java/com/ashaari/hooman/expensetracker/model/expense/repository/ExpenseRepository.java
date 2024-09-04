package com.ashaari.hooman.expensetracker.model.expense.repository;

import com.ashaari.hooman.expensetracker.model.expense.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    Optional<ExpenseEntity> findByIdAndUsername(Long id, String username);

}
