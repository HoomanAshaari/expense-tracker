package com.ashaari.hooman.expensetracker.business.user.validator;

import com.ashaari.hooman.expensetracker.common.dto.SignUpRequestDto;
import com.ashaari.hooman.expensetracker.common.exception.client.UsernameAlreadyExistsException;
import com.ashaari.hooman.expensetracker.model.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserBusinessValidator {

    private final UserRepository userRepository;

    public void validate(SignUpRequestDto signUpRequestDto) {
        validateUserDuplication(signUpRequestDto);
    }

    public void validateUserDuplication(SignUpRequestDto signUpRequestDto) {
        if (userRepository.existsByUsername(signUpRequestDto.username())) {
            throw new UsernameAlreadyExistsException(signUpRequestDto.username());
        }
    }

}
