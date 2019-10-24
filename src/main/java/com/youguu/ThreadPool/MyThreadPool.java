package com.youguu.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池练习
 */
public class MyThreadPool {

    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i <10 ; i++) {
            int temp = i;
            service.execute(new Runnable() {
                @Override
                public void run() {

                    System.out.println("当前线程名称："+Thread.currentThread().getName()+",i:"+ temp);
                }
            });
        }

        ExecutorService  fix = Executors.newFixedThreadPool(3);
        for (int i = 0; i <10 ; i++) {
            int temp = i;
            fix.execute(new Runnable() {
                @Override
                public void run() {

                    System.out.println("当前线程名称："+Thread.currentThread().getName()+",i:"+ temp);
                }
            });
        }

        Executors.newScheduledThreadPool(3);

    }
}
