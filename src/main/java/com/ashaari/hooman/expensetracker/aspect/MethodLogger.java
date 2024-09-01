package com.ashaari.hooman.expensetracker.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MethodLogger {

    @Pointcut("@within(Logged)")
    public void loggingPointcut() {}

    @Around(value = "loggingPointcut()")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            log.info(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        }
    }

}
