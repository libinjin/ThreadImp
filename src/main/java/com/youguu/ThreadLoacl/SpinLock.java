
package com.youguu.ThreadLoacl;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁实现
 */
public class SpinLock {

    private AtomicReference<Thread> sign = new AtomicReference<>();

    /**
     * 当预期不是null时，释放锁
     */
    public void lock(){
        Thread current = Thread.currentThread();

        while (!sign.compareAndSet(null, current)){

        }
    }

    public void unlock(){
        Thread current = Thread.currentThread();
        sign.compareAndSet(current, null);
    }

    public static void main(String[] args) {

        SpinLock spinLock = new SpinLock();

        final int[] sum = {0};
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(){
                @Override
                public void run() {
                    spinLock.lock();
                    //spinLock.lock(); 不能嵌套，会造成死锁
                    sum[0]++;
                    System.out.println(sum[0]);
                    spinLock.unlock();
                    spinLock.unlock();
                }
            };
            thread.start();
        }

    }



}
