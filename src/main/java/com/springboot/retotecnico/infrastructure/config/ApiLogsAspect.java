package com.springboot.retotecnico.infrastructure.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ApiLogsAspect {

    @Pointcut("execution(* com.springboot.retotecnico.infrastructure.controller.*.* (..))")
    public void allRequest (){
    }

    @Before("allRequest()")
    public void adviceBeforeRequest (JoinPoint jp){
        String methodName = jp.getSignature().getName();
        log.info("INIT: " + methodName);
    }

    @AfterReturning(pointcut = "allRequest()")
    public void adviceAfterSuccesfulRequest (JoinPoint jp){
        String methodName = jp.getSignature().getName();
        log.info("END: " + methodName);
    }

}
