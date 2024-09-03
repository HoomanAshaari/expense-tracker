package com.ashaari.hooman.expensetracker.business.user.service;

import com.ashaari.hooman.expensetracker.common.dto.LoginRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.LoginResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.SignUpRequestDto;
import com.ashaari.hooman.expensetracker.common.exception.client.InvalidCredentialsException;
import com.ashaari.hooman.expensetracker.common.exception.client.UsernameAlreadyExistsException;
import com.ashaari.hooman.expensetracker.model.user.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    /**
     * Sign-ups a user
     *
     * @param signUpRequestDto user to be signed-up
     * @throws UsernameAlreadyExistsException if user with given username already exists
     */
    @Transactional
    void signUp(SignUpRequestDto signUpRequestDto);

    /**
     * Saves the given user
     *
     * @param userEntity user to be saved
     * @return saved user
     */
    @Transactional
    UserEntity saveUser(UserEntity userEntity);

    /**
     * Logins the given user
     *
     * @param loginRequestDto user-info to be logged in
     * @return saved user
     * @throws InvalidCredentialsException if given credentials are not valid
     */
    @Transactional
    LoginResponseDto loginUser(LoginRequestDto loginRequestDto);
}
