package com.youguu.ThreadLoacl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParseDate implements Runnable{

    static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>(){

        protected void finalize() {
            System.out.println("first的"+this.toString()+" ,is gc");
        }
    };

    static volatile CountDownLatch cd = new CountDownLatch(1000);

    int i = 0;

    public ParseDate(int i) {
        this.i = i;
    }

    @Override
    public void run() {

        try {

            if(threadLocal.get() == null){
                threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"){
                    protected void finalize() {
                        System.out.println("second的"+this.toString()+" ,is gc");
                    }
                });
                //System.out.println("当前id为："+Thread.currentThread().getId()+"创建的create SimpleDateFormat");
            }
            SimpleDateFormat sdf = threadLocal.get();
            Date date = sdf.parse("2019-09-10 23:17:"+i%60);

            //System.out.println(i+":"+date);
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            cd.countDown();
        }

    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 1000; i++) {
            executor.execute(new ParseDate(i));
        }

        cd.await();

        System.out.println("mission complete!");

        threadLocal = null;

        System.gc();

        System.out.println("第一次执行gc完毕");

        threadLocal = new ThreadLocal<SimpleDateFormat>();

        cd = new CountDownLatch(1000);

        for (int i = 0; i < 1000; i++) {
            executor.execute(new ParseDate(i));
        }

        cd.await();

        Thread.sleep(10000);
        System.gc();

        System.out.println("第二次执行gc完毕");

        executor.shutdown();

    }
}
