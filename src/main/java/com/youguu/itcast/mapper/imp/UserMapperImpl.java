package com.youguu.itcast.mapper.imp;


import com.youguu.itcast.mapper.UserMapper;

public class UserMapperImpl implements UserMapper {

    @Override
    public void addUser(String username, String password) {
        System.out.println("-------UserManagerImpl.add()-----------");
    }

    @Override
    public void delUser(int userId) {
        System.out.println("-------UserManagerImpl.delUser()-----------");
    }

    @Override
    public String findUser(int userId) {
        System.out.println("-------UserManagerImpl.findUser()-----------");
        return "张三";
    }

    @Override
    public void modifyUser(int userId, String username, String password) {
        System.out.println("-------UserManagerImpl.modifyUser()-----------");
    }

}
