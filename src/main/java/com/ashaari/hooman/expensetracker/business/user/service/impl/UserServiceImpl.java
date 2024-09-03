package com.ashaari.hooman.expensetracker.business.user.service.impl;

import com.ashaari.hooman.expensetracker.business.user.service.UserService;
import com.ashaari.hooman.expensetracker.business.user.validator.UserBusinessValidator;
import com.ashaari.hooman.expensetracker.common.dto.SignUpRequestDto;
import com.ashaari.hooman.expensetracker.model.user.entity.UserEntity;
import com.ashaari.hooman.expensetracker.model.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserBusinessValidator userValidator;

    @Override
    public void signUp(SignUpRequestDto signUpRequestDto) {
        userValidator.validate(signUpRequestDto);
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

}
