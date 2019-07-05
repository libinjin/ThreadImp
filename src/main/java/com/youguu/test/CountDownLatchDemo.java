package com.youguu.test;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {


        ExecutorService pool = Executors.newCachedThreadPool();


        Thread t= new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId()+"牛人进来时间："+new Date());
                Cach.setCount();
                System.out.println(Thread.currentThread().getId()+"牛人加载完成的时间："+new Date());
                togetherToEat();
            }
        };

        pool.execute(t);



        new Thread() {
            public void run() {
                try {
                    Thread.sleep(999);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Another another = new Another();
                try {
                    another.get();
                    System.out.println("第一次加载完数据"+new Date());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        for (int i = 0; i < 2; i++) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getId()+"牛人进来时间："+new Date());
                    Cach.setCount();
                    System.out.println(Thread.currentThread().getId()+"牛人加载完成的时间："+new Date());
                }
            }.start();
        }

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getId()+"牛人进来时间："+new Date());
                Cach.setCount();
                System.out.println(Thread.currentThread().getId()+"牛人加载完成的时间："+new Date());
            }
        }.start();

      /*  new Thread() {
            public void run()
            {
                Another another = new Another();
                try {
                    Thread.sleep(8000);
                    another.get();
                    System.out.println("第二次加载完数据"+new Date());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("第二次牛人进来时间："+new Date());
                if(Cach.flag){
                    try {
                        Cach.getSingleCount().await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("第二次牛人加载完成的时间："+new Date());
                togetherToEat();
            }
        }.start();
*/
    }

    public static void fatherToRes()
    {
        System.out.println("爸爸步行去饭店需要3小时。");
    }

    /**
     * 模拟我去饭店
     */
    public static void motherToRes()
    {
        System.out.println("妈妈挤公交去饭店需要2小时。");
    }

    /**
     * 模拟妈妈去饭店
     */
    public static void meToRes()
    {
        System.out.println("我乘地铁去饭店需要1小时。");
    }

    /**
     * 模拟一家人到齐了
     */
    public static void togetherToEat()
    {
        System.out.println("解析完数据，开始吃饭");
    }

}
