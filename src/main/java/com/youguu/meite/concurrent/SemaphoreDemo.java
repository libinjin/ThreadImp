package com.youguu.meite.concurrent;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {
        //最多支持多个资源访问
        Semaphore semaphore = new Semaphore(5);

        for (int i = 1; i <= 10; i++) {
            Parent parent = new Parent(semaphore, "ming"+i);
            new Thread(parent).start();
        }

    }
}

class Parent implements Runnable{

    Semaphore semaphore;

    String name;

    public Parent(Semaphore semaphore, String name) {
        this.semaphore = semaphore;
        this.name = name;
    }

    @Override
    public void run() {

        int available = semaphore.availablePermits();
        System.out.println("当前坑数："+available);
        if(available > 0){
            System.out.println(name+" ,有坑了");
        }else{
            System.out.println("一个坑都没有了,"+name+"进行等待");
        }
        try {
            semaphore.acquire();
            System.out.println(name+" ,能上厕所了");
            Thread.sleep(500);//
            System.out.println(name+" ,上完了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }
}