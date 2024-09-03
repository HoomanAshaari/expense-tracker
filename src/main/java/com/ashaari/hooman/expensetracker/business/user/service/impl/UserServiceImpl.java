package com.ashaari.hooman.expensetracker.business.user.service.impl;

import com.ashaari.hooman.expensetracker.business.user.service.UserService;
import com.ashaari.hooman.expensetracker.business.user.validator.UserBusinessValidator;
import com.ashaari.hooman.expensetracker.common.dto.LoginRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.LoginResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.SignUpRequestDto;
import com.ashaari.hooman.expensetracker.model.user.entity.UserEntity;
import com.ashaari.hooman.expensetracker.model.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserBusinessValidator userValidator;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void signUp(SignUpRequestDto signUpRequestDto) {
        userValidator.validate(signUpRequestDto);
        UserEntity userEntity = new UserEntity(signUpRequestDto.username(), signUpRequestDto.email());
        String hashedPassword = passwordEncoder.encode(signUpRequestDto.password());
        userEntity.setPassword(hashedPassword);
        saveUser(userEntity);
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public LoginResponseDto loginUser(LoginRequestDto loginRequestDto) {
        // Authenticate
        // Generate token
        // Return response
        return null;
    }

}
