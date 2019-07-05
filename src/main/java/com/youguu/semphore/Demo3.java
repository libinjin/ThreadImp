package com.youguu.semphore;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Demo3 {

    private final static Semaphore mutex = new Semaphore(1);
    private final static Semaphore ant = new Semaphore(4);
    static boolean flag = false;
    public static void main(String[] args) {

        new Thread(){
            @Override
            public void run() {
                try {
                    mutex.acquire();
                    flag = true;
                    System.out.println(Thread.currentThread().getId()+"加载数据"+new Date());
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mutex.release();
                    flag = false;
                }
            }
        }.start();


        ExecutorService pools = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {

            final int index = i;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        mutex.acquire();
                        System.out.println(flag);
                        System.out.println(String.format("[Thread-%s]执行任务id --- %s",Thread.currentThread().getId(),index)+new Date());
                        TimeUnit.SECONDS.sleep(2);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        //使用完成释放锁
                        mutex.release();
                        //System.out.println("-----------release");
                    }
                }
            };
            pools.execute(run);
        }
        pools.shutdown();
    }
}
