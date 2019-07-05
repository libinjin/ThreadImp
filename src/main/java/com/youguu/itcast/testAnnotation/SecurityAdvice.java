package com.youguu.itcast.testAnnotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 横切面关注组件
 */
@Aspect
@Component("securityAdvice")
public class SecurityAdvice {

    /**
     * 织入点Joinpoint
     */
    @Pointcut("execution(* com.youguu.itcast.testAnnotation..*.*(..)))")
    //@Pointcut("execution(* add*(..))")
    public void pointcut(){}

    /**
     * 执行时机
     * 具体实现Advice
     */
    @Around("pointcut()")
    public Object execute(ProceedingJoinPoint point) throws Throwable {

        System.out.println("安全检查前执行");

        Object object = point.proceed();

        System.out.println("安全检查后执行"+object);

        return object;
    }

}
