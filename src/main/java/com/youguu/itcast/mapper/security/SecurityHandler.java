package com.youguu.itcast.mapper.security;


import com.youguu.itcast.mapper.UserMapper;
import com.youguu.itcast.mapper.imp.UserMapperImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 运行时创建的叫做动态代理，
 * 提前写好的，在编译时创建的叫做静态代理
 */
public class SecurityHandler implements InvocationHandler {


    private Object object;

    public Object createProxyInstance(Object targetObject){
        this.object = targetObject;
        return  Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(),
                this);
    }


    /**
     * 写要横切的方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        checkSecurity();

        //调用目标方法
        return method.invoke(object, args);
    }

    /**
     * 检测完全性
     */
    private void checkSecurity(){
        System.out.println("check安全性");
    }

    public static void main(String[] args) {
        SecurityHandler handler = new SecurityHandler();
        //传入的目标必须实现接口
        /**
         * JDK动态代理必须有一个接口类
         */
        UserMapper userMapper = (UserMapper) handler.createProxyInstance(new UserMapperImpl());

        userMapper.addUser("libn","123");
    }
}
