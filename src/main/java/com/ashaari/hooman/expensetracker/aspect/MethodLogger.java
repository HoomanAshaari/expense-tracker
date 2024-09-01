package com.ashaari.hooman.expensetracker.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MethodLogger {

    @Pointcut("execution(@com.ashaari.hooman.expensetracker.aspect.Logged * *(..))")
    public void loggingPointcut() {}


}
