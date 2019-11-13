package com.youguu.threads.CAS;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {


    static AtomicInteger atomicInteger = new AtomicInteger();

    public static class AddThread implements Runnable{

        //给atomic自增
        @Override
        public void run() {

            for (int i = 0; i < 10000; i++) {
                atomicInteger.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread[] thread = new Thread[10];

        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(new AddThread());
            thread[i].start();
        }

        for (int i = 0; i < 10; i++){
            thread[i].join();
        }

        System.out.println(atomicInteger);
    }
}
