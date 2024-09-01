package com.ashaari.hooman.expensetracker.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class MethodLogger {

    @Pointcut("@within(Logged)")
    public void loggingPointcut() {
    }

    @Around(value = "loggingPointcut()")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("Method got invoked: ")
                .append(joinPoint.getSignature().getDeclaringTypeName())
                .append(".")
                .append(joinPoint.getSignature().getName())
                .append(" - Input parameters: ")
                .append(Arrays.toString(joinPoint.getArgs()));
        try {
            Object response = joinPoint.proceed();
            logMessage.append(" - Response: ").append(response);
            return response;
        } catch (Throwable throwable) {
            String exception = throwable.getClass().getName() + ": " + throwable.getMessage();
            logMessage.append(" - Exception has been thrown: ").append(exception);
            throw throwable;
        } finally {
            log.info(logMessage.toString());
        }
    }

}
