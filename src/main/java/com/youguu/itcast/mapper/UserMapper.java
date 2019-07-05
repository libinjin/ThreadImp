package com.youguu.itcast.mapper;

public interface UserMapper {

    public void addUser(String username, String password);

    public void delUser(int userId);

    public String findUser(int userId);

    public void modifyUser(int userId, String username, String password);

}
