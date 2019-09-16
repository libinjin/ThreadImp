package com.youguu.meite;

/**
 * 线程停止练习
 */
public class ThreadStop {


    public static void main(String[] args) throws InterruptedException {
        StopDemo stopDemo = new StopDemo();
        stopDemo.start();

        for (int i = 0; i < 6; i++) {
            System.out.println("我是主线程："+i);
            Thread.sleep(1000);

            if(i == 5){
                //让线程抛出异常
                stopDemo.interrupt();
                //stopDemo.stopThread();
            }
        }
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
                stopThread();
            }
        }
        System.out.println("子线程结束执行...");
    }
}