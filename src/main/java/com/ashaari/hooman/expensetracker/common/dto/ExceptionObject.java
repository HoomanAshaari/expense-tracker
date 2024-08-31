package com.ashaari.hooman.expensetracker.common.dto;

/**
 * General object, mostly used in APIs responses, for returning exceptions gracefully.
 */
public record ExceptionObject(String errorCode, String errorMessage) {
}
