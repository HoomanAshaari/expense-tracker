package com.ashaari.hooman.expensetracker.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate methods that you want to enable logging for.
 * (If you do so, inputs, outputs, and   exceptions (if
 * happen) will be logged)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Logged {

}
