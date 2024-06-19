package com.example.projektJava.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {


    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Pointcut("execution(* com.example.projektJava.service.*.*(..))")
    public void applicationPackagePointcut() {
        // Metoda jest pusta bo to pointcut implementacje w metodach xd
    }


    @Before("applicationPackagePointcut()")
    public void logBefore() {
        logger.info("A method is about to be executed.");
    }

    @AfterReturning(pointcut = "applicationPackagePointcut()", returning = "result")
    public void logAfterReturning(Object result) {
        logger.info("Metoda została wykonana i zwróciła: " + result);
    }
}
