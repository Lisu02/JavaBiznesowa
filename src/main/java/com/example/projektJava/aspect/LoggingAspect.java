package com.example.projektJava.aspect;

import org.aspectj.lang.annotation.*;
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
       // logger.info("Metoda zaraz zostanie wykonana.");
    }

    @AfterReturning(pointcut = "applicationPackagePointcut()", returning = "result")
    public void logAfterReturning(Object result) {
        //logger.info("Metoda została wykonana i zwróciła: " + result);

    }
}
