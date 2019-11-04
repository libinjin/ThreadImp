package com.youguu.reflect.classLoader;

public class User {

    public void add(){
        System.out.println("user1.0的add方法");
    }

    public static void main(String[] args) {
        User user = new User();
        user.add();
    }
}
