package com.youguu.spring.springIOC.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test001 {

    public static void main(String[] args) throws DocumentException {

/*        SAXReader reader = new SAXReader();


        Document document = reader.read("");

        document.getRootElement();*/

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring_02.xml");

        UserServiceImpl userService = (UserServiceImpl) ctx.getBean("userService");

        userService.add();

       /* UserServiceImpl userService = new UserServiceImpl();
        userService.add();*/
    }
}
