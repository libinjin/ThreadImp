package com.youguu.reflect;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;

/**
 * 使用java字节码技术创建字节码
 *
 *
 */
public class Entity {

    private String name;
    private Integer age;

    public Entity() {
    }

    /**
     * 1、可以使用字节码技术对类的基本信息做操作，新增属性或者
     *    方法、类、修改属性或者方法，删除属性或者方法
     * 2、动态代理
     * 3、使用字节码技术创建class文件；对一个类实现新增方法
     *    动态的执行新增方法
     */
    public static void main(String[] args) {

        ClassPool pool = ClassPool.getDefault();

        //创建user类
        CtClass useClass = pool.makeClass("com.youguu.reflect.pojo");

        //2.创建属性
        //CtField.make("", );
    }
}
