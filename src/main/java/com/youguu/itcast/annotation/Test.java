package com.youguu.itcast.annotation;

import com.youguu.itcast.annotation.user.UserMapper;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {

        BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = (UserMapper) factory.getBean("userMapper");
        userMapper.addUser("","");
    }
}
