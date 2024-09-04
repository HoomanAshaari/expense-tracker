package com.ashaari.hooman.expensetracker.model.expense.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "expense")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    // We avoided adding direct relation with UserEntity which has sensitive data
    @Column(nullable = false)
    private String username;

    private LocalDateTime spentOn;

    @PrePersist
    void prePersist() {
        if (this.spentOn == null) {
            this.spentOn = LocalDateTime.now();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExpenseEntity that)) return false;
        return id != null &&
                Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "ExpenseEntity{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", spentOn=" + spentOn +
                '}';
    }

}
