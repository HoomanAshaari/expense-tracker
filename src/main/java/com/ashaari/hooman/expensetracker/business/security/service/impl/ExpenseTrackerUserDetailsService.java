package com.ashaari.hooman.expensetracker.business.security.service.impl;

import com.ashaari.hooman.expensetracker.model.user.entity.UserEntity;
import com.ashaari.hooman.expensetracker.model.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseTrackerUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return User.builder()
                .username(userEntity.get().getUsername())
                .password(userEntity.get().getPassword())
                .build();
    }

}
