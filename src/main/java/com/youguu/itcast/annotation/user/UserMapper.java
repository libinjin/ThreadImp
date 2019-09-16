package com.youguu.itcast.annotation.user;

public interface UserMapper {

    void addUser(String username, String password);

    void delUser(int userId);

    String findUser(int userId);

    void modifyUser(int userId, String username, String password);

}
