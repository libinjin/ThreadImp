package com.youguu.itcast.testAnnotation;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapperImp userMapper = (UserMapperImp) factory.getBean("userMapperImp");
        userMapper.addUser("","");
        userMapper.findUser(3);
    }
}
