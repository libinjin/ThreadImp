package com.youguu.reflect;

import javassist.ClassPool;
import javassist.NotFoundException;

/**
 * 动态修改字节码文件
 */
public class DynamicSetClass {

    private String name;
    private Integer id;

    public static void main(String[] args) throws NotFoundException {

        ClassPool pool = ClassPool.getDefault();
        //pool.get("")
    }
}
