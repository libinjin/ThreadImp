package com.youguu.meite.concurrent.AQS;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private int value;

    private MyAQSLock myAQSLock = new MyAQSLock();

    public int next(){
        myAQSLock.tryLock();
        try {
            Thread.sleep(300);
            System.out.println(Thread.currentThread().getName()+"在执行next()方法");
            return value++;
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }finally {
            myAQSLock.unlock();
        }
    }

    public void a(){
        myAQSLock.tryLock();
        System.out.println("a");
        b();
        myAQSLock.unlock();
    }

    public void b(){
        myAQSLock.tryLock();
        System.out.println("b");
        myAQSLock.unlock();
    }

    public static void main(String[] args) {

        Main main = new Main();
        ExecutorService service = Executors.newFixedThreadPool(2);

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                main.a();
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println(Thread.currentThread().getName()+" "+main.next());
                }
            }
        };
        service.execute(r1);
        service.execute(r2);
        service.execute(r2);
        service.execute(r2);
    }
}
