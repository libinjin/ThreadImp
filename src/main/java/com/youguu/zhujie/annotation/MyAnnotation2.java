package com.youguu.zhujie.annotation;


public @interface MyAnnotation2 {

    //注解属性
    int i();

    String s();

    String[] ss();

    Class cl();

    MyAnnotation1 m();

    Color RED();
}
