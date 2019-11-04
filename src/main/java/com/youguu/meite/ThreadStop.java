package com.youguu.meite;

/**
 * 线程停止练习
 */
public class ThreadStop {


    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            Thread.sleep(1000);
            System.out.println("休息一秒");
        }
        /*StopDemo stopDemo = new StopDemo();
        stopDemo.start();

        for (int i = 0; i < 6; i++) {
            System.out.println("我是主线程："+i);
            Thread.sleep(1000);

            if(i == 1){
                //让线程抛出异常
                stopDemo.interrupt();
                //stopDemo.stopThread();
            }
        }*/
    }

}

class StopDemo extends Thread{

    public volatile boolean flag = true;

    public void stopThread(){
        System.out.println("调用stopThread方法");
        this.flag = false;
        System.out.println("应修改flag为："+flag);
    }

    @Override
    public synchronized void run() {
        System.out.println("子线程开始执行...");

        while (flag){
            try {
                wait();

            } catch (InterruptedException e) {
                //e.printStackTrace();

                /**
                 * 第一次调用interrupt，并没有中断正在运行的线程，
                 * 它只是要求线程在自己合适的时机中断自己，
                 * 对正常运行的线程调用interrupt()并不能终止他，只是改变了interrupt标示符
                 *
                 */
                stopThread();
            }
        }
        System.out.println("子线程结束执行...");
    }
}