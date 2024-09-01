package com.ashaari.hooman.expensetracker.api.restcontroller;

import com.ashaari.hooman.expensetracker.common.dto.ExceptionObject;
import com.ashaari.hooman.expensetracker.common.exception.client.ExpenseTrackerClientException;
import com.ashaari.hooman.expensetracker.common.exception.client.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
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
    public ExceptionObject handleResourceNotFoundException(ResourceNotFoundException ex) {
        return getExceptionObject(ex);
    }

    @ExceptionHandler(value = ExpenseTrackerClientException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionObject handleClientException(ExpenseTrackerClientException ex) {
        return getExceptionObject(ex);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionObject handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getFieldError();
        if (fieldError != null) {
            String field = fieldError.getField();
            return new ExceptionObject(
                    VALIDATION_EXCEPTION, "Field \"" + field +
                    "\" has an invalid value of " + fieldError.getRejectedValue());
        }
        return new ExceptionObject(VALIDATION_EXCEPTION, "");
    }

    @ExceptionHandler(value = Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionObject handleThrowable(GlobalExceptionHandler ex) {
        return new ExceptionObject("InternalErrorException", "Something went wrong");
    }

    private static ExceptionObject getExceptionObject(Exception ex) {
        return new ExceptionObject(ex.getClass().getSimpleName(), ex.getMessage());
    }

}
