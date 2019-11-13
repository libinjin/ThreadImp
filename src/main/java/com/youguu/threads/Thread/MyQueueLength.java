package com.youguu.threads.Thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 返回正在等待获取此锁的
 * 线程估计数，
 * 比如有5个线程在，其中1个在调用await()方法
 * 那么调用getQueueLength()方法后返回值时4
 * 说明有4个线程在同时等待lock的释放
 */
public class MyQueueLength {

    private ReentrantLock lock = new ReentrantLock();

    public void serviceMethod1(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"进入方法");

            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyQueueLength queueLength = new MyQueueLength();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    queueLength.serviceMethod1();
                }
            });
            thread.start();
        }
        Thread.sleep(2000);
        System.out.println(queueLength.lock.hasQueuedThreads());
        System.out.println("已经有"+queueLength.lock.getHoldCount()+"线程持有锁");
        System.out.println("正在等待获取锁的线程数有："+queueLength.lock.getQueueLength());
    }

}
