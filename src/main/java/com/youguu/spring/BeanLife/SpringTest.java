package com.youguu.spring.BeanLife;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_02.xml");
        UserEntity userEntity = (UserEntity) applicationContext.getBean("userEntity");

        String userName = userEntity.getUserName();

        System.out.println("userName:"+userName);
    }
}
