package com.youguu.test;

public class Another {

    public void get() throws InterruptedException {


        Cach.flag = true;
        Thread.sleep(5000);
        System.out.println("加载数据库的数据");
        Cach.getSingleCount().countDown();
        Cach.flag = false;

    }
}
