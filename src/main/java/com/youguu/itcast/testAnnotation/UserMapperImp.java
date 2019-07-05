package com.youguu.itcast.testAnnotation;

import org.springframework.stereotype.Service;

@Service("userMapperImp")
public class UserMapperImp {

    public void addUser(String username, String password) {
        System.out.println("-------UserManagerImpl.add()aaaaaaaaaaaa-----------");
    }

    public String findUser(int userId) {
        System.out.println("-------UserManagerImpl.findUser()-----------");
        return "张三";
    }
}
