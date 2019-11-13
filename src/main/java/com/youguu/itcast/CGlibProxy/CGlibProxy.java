package com.youguu.itcast.CGlibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
 import java.lang.reflect.Method;

public class CGlibProxy implements MethodInterceptor {


    private Object targetObject;

    //传入目标代理对象
    public Object getInstance(Object target){
        this.targetObject = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    //不需要目标代理对象，接口
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        System.out.println("开启事务");

        Object invoke = proxy.invoke(targetObject, args);

        System.out.println("提交事务");
        return invoke;
    }

    public static void main(String[] args) {
        CGlibProxy cGlibProxy = new CGlibProxy();

        UserServiceImpl userService = (UserServiceImpl) cGlibProxy.getInstance(new UserServiceImpl());

        userService.addUser("name","123");
    }

}


