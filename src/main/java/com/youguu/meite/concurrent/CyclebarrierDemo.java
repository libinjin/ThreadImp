package com.youguu.meite.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclebarrierDemo {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
            Write write = new Write(cyclicBarrier);
            write.start();
        }
    }
}

class Write extends Thread{

    private CyclicBarrier cyclicBarrier;

    public Write(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        //
        System.out.println(Thread.currentThread().getName()+"开始写入数据...");

        try {
            Thread.sleep(30);
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName()+"写入数据完成...");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}