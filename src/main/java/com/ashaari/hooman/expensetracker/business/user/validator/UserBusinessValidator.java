package com.ashaari.hooman.expensetracker.business.user.validator;

import com.ashaari.hooman.expensetracker.common.dto.SignUpRequestDto;
import com.ashaari.hooman.expensetracker.common.exception.client.EmailAlreadyExistsException;
import com.ashaari.hooman.expensetracker.common.exception.client.UsernameAlreadyExistsException;
import com.ashaari.hooman.expensetracker.model.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class UserBusinessValidator {

    private final UserRepository userRepository;

    public void validate(SignUpRequestDto signUpRequestDto) {
        validateUserDuplication(signUpRequestDto.username());
        validateEmailDuplication(signUpRequestDto.email());
    }

    public void validateUserDuplication(String username) {
        username = username.toLowerCase(Locale.ENGLISH);
        if (userRepository.existsByUsername(username)) {
            throw new UsernameAlreadyExistsException(username);
        }
    }

    public void validateEmailDuplication(String email) {
        email = email.toLowerCase(Locale.ENGLISH);
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email);
        }
    }

}
