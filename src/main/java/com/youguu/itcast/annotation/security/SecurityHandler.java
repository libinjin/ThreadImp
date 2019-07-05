package com.youguu.itcast.annotation.security;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 1.定义Aspect
 * 将横切性关注点模块化
 * 使用@Aspect注解
 *
 *
 * 运行时创建的叫做动态代理，
 * 提前写好的，在编译时创建的叫做静态代理
 */
@Aspect
@Component
public class SecurityHandler {


    /**
     * 定义Pointcut，Pointcut的名称为point()，此方法
     * 没有返回值和参数，该方法就是一个标识com.youguu.itcast.annotation.user
     */
    @Pointcut("execution(* com.youguu.itcast.annotation.user..*.*(..)))")
    private void point(){}
    /**
     * 检测完全性
     * 2.告诉该方法时Advice
     *   及该Advice在目标方法
     *   前还是后执行
     * 3.在Before中指定执行范围
     *
     * 定义Advice，表示我们的Advice应用到哪些
     * Pointcut订阅的Joinpoint上
     */
    @After("point()")
    private void checkSecurity(){
        System.out.println("check安全性");

    }

}
