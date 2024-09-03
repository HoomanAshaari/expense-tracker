package com.ashaari.hooman.expensetracker.api.restcontroller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expense-tracker/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User")
@Validated
public class UsersController {


}
