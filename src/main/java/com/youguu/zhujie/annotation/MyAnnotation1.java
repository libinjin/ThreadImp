package com.youguu.zhujie.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})//TYPE 只能作用在类、接口上面等
@Retention(RetentionPolicy.RUNTIME)//保存在所有阶段（在源码，编译，运行时都会保存，因此可以反射）
public @interface MyAnnotation1 {

    String value();
}
