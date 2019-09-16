package com.youguu.test;

import java.util.concurrent.CountDownLatch;
public class Cach {

    public static CountDownLatch latch = new CountDownLatch(1);

    public static boolean flag;

    public static CountDownLatch getCount(){

        if(latch == null){
            return new CountDownLatch(1);
        }else{
            return latch;
        }
    }

    public static CountDownLatch getSingleCount() {

        //静态方法使用的同步锁用——类名.class
        if (latch == null || latch.getCount() == 0) {// 这样的写法在线程中会造成异常，多个线程同时执行创建对象，所以外边加锁，一次只能一个线程进来

            synchronized (Cach.class){
                if(latch == null || latch.getCount() == 0){
                    latch = new CountDownLatch(1);
                    return latch;
                }else {
                    return latch;
                }
            }

        } else {
            return latch;
        }
    }

    public static void setCount(){
        if(Cach.flag){
            try {
                Cach.getSingleCount().await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CountDownLatch latch = Cach.getSingleCount();

        System.out.println(latch.getCount());

        latch.countDown();

        System.out.println(latch.getCount());

        CountDownLatch newlatch = Cach.getSingleCount();

        System.out.println(newlatch.getCount());

        newlatch.countDown();

        System.out.println(newlatch.getCount());

        System.out.println(latch == newlatch);
    }

}
