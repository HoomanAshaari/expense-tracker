package com.ashaari.hooman.expensetracker.model.expense.repository;

import com.ashaari.hooman.expensetracker.common.dto.CategoryExpenseStatsDto;
import com.ashaari.hooman.expensetracker.model.expense.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    Optional<ExpenseEntity> findByIdAndUsername(Long id, String username);

    @Query("SELECT new com.ashaari.hooman.expensetracker.common.dto.CategoryExpenseStatsDto(" +
            "c.name, SUM(e.amount), c.budget) " +
            "FROM ExpenseEntity e JOIN e.category c " +
            "WHERE e.username = :username AND e.spentOn BETWEEN :dateTimeFrom AND :dateTimeTo " +
            "GROUP BY c.name, c.budget")
    List<CategoryExpenseStatsDto> findCategoryExpenseStatsByDateTimeRange(
            @Param("dateTimeFrom") LocalDateTime dateTimeFrom,
            @Param("dateTimeTo") LocalDateTime dateTimeTo,
            @Param("username") String username);

}
