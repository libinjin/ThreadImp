package com.youguu.threads.semphore;

import java.util.Date;
import java.util.concurrent.Semaphore;

public class SemphoreDemo {

    private static Semaphore semaphore = new Semaphore(1);
    public static void main(String[] args) {




        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("先执行"+new Date());
                try {
                    System.out.println("等待3秒");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaphore.release();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("开始"+new Date());
                semaphore.release();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("第二次先执行"+new Date());
                try {
                    System.out.println("第二次等待3秒");
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaphore.release();
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(7000);
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("第二次开始"+new Date());
                semaphore.release();
            }
        }).start();

    }



}
