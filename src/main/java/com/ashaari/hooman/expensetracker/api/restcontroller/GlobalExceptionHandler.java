package com.ashaari.hooman.expensetracker.api.restcontroller;

import com.ashaari.hooman.expensetracker.common.dto.ExceptionObject;
import com.ashaari.hooman.expensetracker.common.exception.client.ExpenseTrackerClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(value = { ExpenseTrackerClientException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionObject handleClientException(ExpenseTrackerClientException ex) {
        log.error("Exception happened: {}", ex.toString());
        return new ExceptionObject(ex.getClass().getSimpleName(), ex.getMessage());
    }

}
