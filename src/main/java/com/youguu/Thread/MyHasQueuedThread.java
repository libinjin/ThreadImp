package com.youguu.Thread;

import org.springframework.aop.ThrowsAdvice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 查询指定的线程是否在等待获取此锁
 */
public class MyHasQueuedThread {

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    public void waitMethod(){

        try {
            lock.lock();
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyHasQueuedThread queuedThread = new MyHasQueuedThread();
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {

                queuedThread.waitMethod();
            }
        });
        threadA.start();
        Thread.sleep(500);
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {

                queuedThread.waitMethod();
            }
        });
        threadB.start();
        Thread.sleep(500);

        System.out.println(lock.hasQueuedThread(threadA));
        System.out.println(lock.hasQueuedThread(threadB));
        System.out.println(lock.hasQueuedThreads());

    }

}
