package com.ashaari.hooman.expensetracker.api.restcontroller;

import com.ashaari.hooman.expensetracker.business.user.service.UserService;
import com.ashaari.hooman.expensetracker.common.dto.LoginRequestDto;
import com.ashaari.hooman.expensetracker.common.dto.LoginResponseDto;
import com.ashaari.hooman.expensetracker.common.dto.SignUpRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ashaari.hooman.expensetracker.api.restcontroller.util.RestUtils.USERS_API_V_1;

@RestController
@RequestMapping(USERS_API_V_1)
@RequiredArgsConstructor
@Tag(name = "User")
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Signs up (adds a new) user")
    public void signUp(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {
        userService.signUp(signUpRequestDto);
    }

    @PostMapping("/login")
    @Operation(description = "Logins user (generates JWT token)")
    public LoginResponseDto login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        return userService.loginUser(loginRequestDto);
    }

}
