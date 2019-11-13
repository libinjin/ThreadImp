package com.youguu.zhujie;


import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class User {

    @AddAnnotion(userName = "水电费", userId = 18, arrays = {""})
    public void add(){

        System.out.println("sf");
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {


        Class cls = Class.forName("com.youguu.zhujie.User");


        Method[]  methods = cls.getDeclaredMethods();

        for (Method method : methods) {
            //获取该方法上是否存在注解
            boolean has = method.isAnnotationPresent(AddAnnotion.class);
            if(has){
                AddAnnotion annotation = method.getDeclaredAnnotation(AddAnnotion.class);
                int userId = annotation.userId();

                String userName = annotation.userName();

                System.out.println("userId:"+userId);
                System.out.println("userName:"+userName);
            }
        }
    }



}
