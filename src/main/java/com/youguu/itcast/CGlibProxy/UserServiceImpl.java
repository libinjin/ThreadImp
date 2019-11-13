package com.youguu.itcast.CGlibProxy;


import com.youguu.itcast.annotation.user.UserMapper;
import org.springframework.stereotype.Service;

@Service("userMapper")
public class UserServiceImpl implements UserMapper {

    @Override
    public void addUser(String username, String password) {
        System.out.println("-------UserManagerImpl.add()asdf-----------");
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
