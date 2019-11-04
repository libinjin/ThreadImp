package com.youguu.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UserEntity {
    private String userName;

    public UserEntity() {
        System.out.println("对象初始化...");
        //在构造函数中发生异常，创建对象是不成功的
        //throw new RuntimeException();
    }

    private UserEntity(String userName){
        System.out.println("name:"+userName);
        this.userName = userName;
    }




    /**
     *
     * @param args
     */
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        //使用java的反射机制来创建对象,拿到当前实现类的class文件
        Class cls = Class.forName("com.youguu.reflect.UserEntity");
        //使用反射机制创建对象
        UserEntity entity = (UserEntity) cls.newInstance();
        entity.userName = "蚂蚁课堂";
        //System.out.println(entity.userName);
        //获取类中信息
       /* Method[]  methods = cls.getMethods();
        for (Method method : methods){
            System.out.println("该对象中的方法："+method.getName());
        }*/

        //获取类属性，这里没有getFields()方法只能获取到共有方法
       /* Field[] fields = cls.getFields();
        for (Field field : fields) {
            System.out.println("该对象中的属性："+field.getName());
        }*/

        /*Field[] privateFields = cls.getDeclaredFields();
        for (Field field : privateFields) {
            System.out.println("该对象中的私有属性："+field.getName());
        }*/

        //利用反射执行有参数的构成函数
        /*Constructor constructor = cls.getConstructor(String.class);
        UserEntity conUser = (UserEntity) constructor.newInstance("小白");
        System.out.println(entity == conUser);*/

        Constructor constructor1 = cls.getDeclaredConstructor(String.class);
        constructor1.setAccessible(true);
        UserEntity entity1 = (UserEntity) constructor1.newInstance("小白");
        System.out.println(entity1);
    }
}
