package com.youguu.spring.springAOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import java.lang.reflect.Method;

/**
 * 切面类
 */
@Aspect
@Component("AopTransaction")
public class AopTransaction {

    @Autowired
    private TransactionUtils transactionUtils;


    //pointcut
    @Pointcut("execution(* com.youguu.asteroid.strategy.*.*..*.*(..))")
    public void pointcut() {
        System.out.println("--------------事务开始--------------");
    }

    //advice
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        //获取方法名称
        String methodName = joinPoint.getSignature().getName();

        //获取目标对象
        Class<?> classTarget = joinPoint.getTarget().getClass();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

  /*      String[] str = methodSignature.getParameterNames();

        Class<?>[] par = methodSignature.getParameterTypes();

        Method objMethod = classTarget.getMethod(methodName, par);

        objMethod.getDeclaredAnnotation(ExTransaction.class);
*/

        Method method = methodSignature.getMethod();

        boolean has = method.isAnnotationPresent(ExTransaction.class);

        Object result = null;

        if(has){
            TransactionStatus transactionStatus = transactionUtils.begin();

            result = joinPoint.proceed();

            transactionUtils.commit(transactionStatus);
        }
        return result;
    }


    /**
     * 抛异常后回滚
     * 注意：在使用spring事务的时候，service层
     * 不要try、catch异常，将异常抛出即可
     * 才会执行异常通知，执行回滚事务
     * @param joinPoint
     */
    @AfterThrowing("execution(* com.youguu.asteroid.strategy.dao.impl..*.*(..))")
    public void afterThrowing(ProceedingJoinPoint joinPoint){

        System.out.println("回滚事务");
        //获取当前事务
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }

}
