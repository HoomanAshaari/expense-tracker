package com.ashaari.hooman.expensetracker.business.user.service;

import com.ashaari.hooman.expensetracker.common.dto.SignUpRequestDto;
import com.ashaari.hooman.expensetracker.common.exception.client.UsernameAlreadyExistsException;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    /**
     * Sign-up a user
     *
     * @param signUpRequestDto user to be signed-up
     * @throws UsernameAlreadyExistsException if user with given username already exists
     */
    @Transactional
    void signUp(SignUpRequestDto signUpRequestDto);

}
