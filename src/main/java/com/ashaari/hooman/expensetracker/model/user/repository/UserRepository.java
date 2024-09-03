package com.ashaari.hooman.expensetracker.model.user.repository;

import com.ashaari.hooman.expensetracker.model.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
