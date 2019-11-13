package com.youguu.threads.Thread;

public class IsAlive extends Thread{

    @Override
    public void run() {
        System.out.println("name:"+Thread.currentThread().getName()+" ，里面interrupt:"+this.isInterrupted());
        super.run();
        System.out.println("name:"+Thread.currentThread().getName()+" ，run="+this.isAlive());

    }

    public static void main(String[] args) {
        IsAlive isAlive = new IsAlive();
        System.out.println("name:"+Thread.currentThread().getName()+" ，begin:"+isAlive.isAlive());
        isAlive.start();
        System.out.println("name:"+Thread.currentThread().getName()+" ，interrupt:"+isAlive.isInterrupted());

        System.out.println("name:"+Thread.currentThread().getName()+" ，after:"+isAlive.isAlive());
        isAlive.interrupt();
        System.out.println("name:"+Thread.currentThread().getName()+" ，interrupt:"+isAlive.isInterrupted());
    }
}
