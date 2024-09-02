package com.ashaari.hooman.expensetracker.model.expense.entity;

import jakarta.persistence.*;

@Entity
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

}
