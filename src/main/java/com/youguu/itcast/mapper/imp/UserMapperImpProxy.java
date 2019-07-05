package com.youguu.itcast.mapper.imp;


import com.youguu.itcast.mapper.UserMapper;

/**
 * 静态代理
 */
public class UserMapperImpProxy implements UserMapper {

   private UserMapper userMapper;

    public UserMapperImpProxy(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void addUser(String username, String password) {
        checkSecurity();
        userMapper.addUser(username, password);
    }

    @Override
    public void delUser(int userId) {
        userMapper.delUser(userId);
    }

    @Override
    public String findUser(int userId) {
        return null;
    }

    @Override
    public void modifyUser(int userId, String username, String password) {

    }

    /**
     * 检测完全性
     */
    private void checkSecurity(){
        System.out.println("check安全性");
    }
}
