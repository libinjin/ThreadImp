package com.youguu.security;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 自定义一个安全管理器类
 */
public class SecurityRead {

    public static void main(String[] args) throws FileNotFoundException {

        //当设置了自己的安全检查类时，就不能读取.java文件了
        System.setSecurityManager(new MySecurityManager());

        FileInputStream fis = new FileInputStream("");

    }
}
