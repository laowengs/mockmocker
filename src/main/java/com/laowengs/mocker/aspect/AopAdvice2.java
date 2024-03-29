package com.laowengs.mocker.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopAdvice2 {

    @Pointcut("execution (* com.laowengs.mocker.controller.*.*(..))")
    public void test() {

    }

    @Before("test()")
    public void beforeAdvice() {
        System.out.println("beforeAdvice2..");
    }

    @After("test()")
    public void afterAdvice() {
        System.out.println("afterAdvice2...");
    }

    @Around("test()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("before2");
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        System.out.println("after2");
        return null;
    }

}
