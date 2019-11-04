package com.youguu.meite;

public class ThreaInterupt {

    public static void main(String[] args) {
            Thread thread = new Thread(){
                @Override
                public synchronized void run() {
                    System.out.println("正在执行任务");

                    try {
                        wait();//阻塞
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("是否中断"+Thread.currentThread().isInterrupted());
                        Thread.currentThread().interrupt();
                        System.out.println("是否中断"+Thread.currentThread().isInterrupted());
                    }
                }
            };
            thread.start();
            //thread.interrupt();

    }
}
