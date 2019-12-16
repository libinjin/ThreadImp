package com.youguu.spring.mybatis.mapper;

import com.youguu.spring.mybatis.annotation.ExtInsert;
import com.youguu.spring.mybatis.annotation.ExtParam;
import com.youguu.spring.mybatis.annotation.ExtSelect;
import com.youguu.spring.mybatis.entity.User;

public interface UserMapper {

    @ExtInsert("insert INTO USER(userName, userAge) values(#{userName},#{userAge})")
    public int insertUser(@ExtParam("userName")String userName, @ExtParam("userAge") int userAge);

    @ExtSelect("select * from user where userId=#{userId}")
    public User selectUserById(@ExtParam("userId") int userId);

}
