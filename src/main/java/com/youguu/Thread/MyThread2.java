package com.youguu.Thread;

public class MyThread2 extends Thread{

    private int count = 5;

    @Override
    public synchronized void run() {
        count--;
        System.out.println("由"+ currentThread().getName()+"计算出count："+count);
    }

    public static void main(String[] args) {
        MyThread2  myThread2 = new MyThread2();
        Thread a = new Thread(myThread2 , "A");
        Thread b = new Thread(myThread2 , "B");
        Thread c = new Thread(myThread2 , "C");
        Thread d = new Thread(myThread2 , "D");
        Thread e = new Thread(myThread2 , "E");
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
    }
}
