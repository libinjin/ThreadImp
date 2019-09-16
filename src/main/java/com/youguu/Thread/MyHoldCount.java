package com.youguu.Thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 查询当前线程保持此锁定的次数
 * 调用lock()方法的次数
 */
public class MyHoldCount {

    private ReentrantLock lock = new ReentrantLock();

    private void serviceMethod1(){
        try {
            lock.lock();
            System.out.println("serviceMethod1 getHoldCount:"+lock.getHoldCount());
            serviceMethod2();
        } finally {
            lock.unlock();
        }
    }

    private void serviceMethod2(){
        try {
            lock.lock();
            System.out.println("serviceMethod2 getHoldCount:"+lock.getHoldCount());
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyHoldCount holdCount = new MyHoldCount();
        holdCount.serviceMethod1();
    }
}
