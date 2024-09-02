package com.ashaari.hooman.expensetracker.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate classes that you want to enable logging for.
 * If you do so, inputs, outputs,  and   exceptions  (if
 * happen) of all the methods within  the class will  be
 * logged.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Logged {

}
