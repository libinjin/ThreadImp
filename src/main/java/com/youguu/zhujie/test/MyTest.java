package com.youguu.zhujie.test;

public class MyTest {

    public void f1(Integer i){
        System.out.println("f1方法执行了~~~"+i);
    }

    @MyTests("tade")
    public void f2(){
        System.out.println("f2方法执行了~~~");
    }

    @MyTests("shede")
    public void f3(){
        System.out.println("f3方法执行了~~~");
    }

}
