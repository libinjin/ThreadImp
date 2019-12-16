package com.youguu.spring.mybatis.sql;

import com.youguu.spring.mybatis.entity.User;
import com.youguu.spring.mybatis.mapper.UserMapper;
import com.youguu.spring.mybatis.proxy.MyInvocationHandlerMybatis;
import java.lang.reflect.Proxy;

public class SqlSession {

    //加载Mapper接口
    public static <T>T getMapper(Class classz){

        return (T) Proxy.newProxyInstance(classz.getClassLoader(), new Class[]{classz},
                new MyInvocationHandlerMybatis(classz));
    }

    public static void main(String[] args) {
        System.out.println("代理开始");

        UserMapper userMapper = SqlSession.getMapper(UserMapper.class);

/*
        int update = userMapper.insertUser("as",11);

        System.out.println(update);
*/


        User user = userMapper.selectUserById(1);
        System.out.println(user);
    }
}
