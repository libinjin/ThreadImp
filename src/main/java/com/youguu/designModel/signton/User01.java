package com.youguu.designModel.signton;

/**
 * 饿汉式
 */
public class User01 {

    private String userName;

    //类初始化的时候，就会创建对象，天生线程安全，调用效率高

    private static final User01 user = new User01();
    //1.将构造函数私有化
    private User01(){
    }

    /**
     * 不会产生线程安全问题
     * @return
     */
    public static User01 getInstance(){
        return user;
    }
}
