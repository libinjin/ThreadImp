package com.youguu.security;

public class MySecurityManager extends SecurityManager{


    /**
     * 限制读的权限
     * @param file
     */
    @Override
    public void checkRead(String file) {

        if(file.endsWith(".java")){
            throw new SecurityException("不允许访问java文件");
        }
    }
}
