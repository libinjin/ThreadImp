package com.youguu.meite;

public class ThreadVolatile {

    public static void main(String[] args) throws InterruptedException {
        ThreadVolatileDemo demo = new ThreadVolatileDemo();
        demo.start();
        Thread.sleep(3000);
        demo.setFlag(false);
        System.out.println("flag已经修改为false");
        Thread.sleep(1000);
        System.out.println(demo.flag);
    }
}

class ThreadVolatileDemo extends Thread{

    //本地内存中如果没有加volatile时，当其修改时，其他线程不可见
    public volatile boolean flag = true;

    @Override
    public void run() {
        System.out.println("子线程开始执行...");

        while (flag){

        }
        System.out.println("子线程执行结束...");
    }

    public void setFlag(boolean flag){
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }
}