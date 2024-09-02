package com.ashaari.hooman.expensetracker.model.expense.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    /**
     * User specified budget for this category.
     */
    private BigDecimal budget;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ExpenseEntity> expenses;

    public void addExpense(ExpenseEntity expense) {
        if (this.expenses == null) {
            this.expenses = new ArrayList<>();
        }
        this.expenses.add(expense);
        expense.setCategory(this);
    }

    public void removeAllExpenses() {
        for (ExpenseEntity expense : expenses) {
            removeExpense(expense);
        }
    }

    public void removeExpense(ExpenseEntity expense) {
        expense.setCategory(null);
        if (this.expenses != null) {
            this.expenses.remove(expense);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryEntity that)) return false;
        return id != null &&
                Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}


