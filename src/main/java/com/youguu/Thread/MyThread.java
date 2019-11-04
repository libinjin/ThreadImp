package com.youguu.Thread;

public class MyThread extends Thread{

    private int count = 5 ;

    public MyThread(String name) {
        super(name);
        this.setName(name);
    }

    @Override
    public void run() {


        super.run();

        while (count > 0 ){
            count--;
            System.out.println("由"+ currentThread().getName()+"计算出count："+count);
        }
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread("A");
        MyThread t2 = new MyThread("B");
        MyThread t3 = new MyThread("C");
        t1.start();
        t2.start();
        t3.start();
    }
}
