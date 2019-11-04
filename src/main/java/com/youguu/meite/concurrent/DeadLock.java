package com.youguu.meite.concurrent;

public class DeadLock implements Runnable{

    public boolean flag = true;//有货时才能卖

    private volatile static int count = 100;

    private static Object obj = new Object();

    public static byte[] bytes = new byte[100 * 1024];

    //卖
    public synchronized void sale(){

        synchronized (obj){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            if(count > 0){
                System.out.println(Thread.currentThread().getName()+",出售"+(100-count+1)+"张票");
                count--;
            }
        }
    }

    @Override
    public void run() {

        if(flag){
            while (count>0){
                synchronized (obj){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sale();
                }
            }
        }else{
            while (count>0){
                sale();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DeadLock deadLock = new DeadLock();
        Thread sell = new Thread(deadLock,"窗口1");
        Thread sell2 = new Thread(deadLock,"窗口2");

        sell.start();

        Thread.sleep(400);

        deadLock.flag = false;

        sell2.start();
    }

}
