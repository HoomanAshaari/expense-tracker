package com.ashaari.hooman.expensetracker.api.restcontroller;

import com.ashaari.hooman.expensetracker.common.dto.ExceptionDto;
import com.ashaari.hooman.expensetracker.common.exception.client.ExpenseTrackerClientException;
import com.ashaari.hooman.expensetracker.common.exception.client.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    private static final String VALIDATION_EXCEPTION = "ValidationException";

    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleResourceNotFoundException(ResourceNotFoundException ex) {
        return getExceptionObject(ex);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionDto handleBadCredentialsException(BadCredentialsException ex) {
        return getExceptionObject(ex);
    }

    @ExceptionHandler(value = ExpenseTrackerClientException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleClientException(ExpenseTrackerClientException ex) {
        return getExceptionObject(ex);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getFieldError();
        if (fieldError != null) {
            String field = fieldError.getField();
            return new ExceptionDto(
                    VALIDATION_EXCEPTION, "Field \"" + field +
                    "\" has an invalid value of " + fieldError.getRejectedValue());
        }
        return new ExceptionDto(VALIDATION_EXCEPTION, "");
    }

    @ExceptionHandler(value = Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handleThrowable(@SuppressWarnings("unused") GlobalExceptionHandler ex) {
        return new ExceptionDto("InternalErrorException", "Something went wrong");
    }

    private static ExceptionDto getExceptionObject(Exception ex) {
        return new ExceptionDto(ex.getClass().getSimpleName(), ex.getMessage());
    }

}
