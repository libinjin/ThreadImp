package com.youguu.threads.ThreadLoacl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 要及时处理ThreadLocal中的大对象
 * 防止内存侧漏
 */
public class ThreadLocalGC {

    static ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<SimpleDateFormat>(){

        protected void finalize() {
            System.out.println(this.toString()+"is gc");
        }
    };

    static volatile CountDownLatch cd = new CountDownLatch(1000);

    public static class ParseDate implements Runnable{
        int i = 0;
        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if(tl.get() == null){
                    tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"){
                        protected void finalize() {
                            System.out.println(this.toString()+"is gc");
                        }
                    });
                    System.out.println("当前id为："+Thread.currentThread().getId()+"创建的create SimpleDateFormat");
                }

                Date t = tl.get().parse("2019-09-10 23:17:"+i%60);
                //System.out.println(Thread.currentThread().getId()+":时间："+t);
            } catch (ParseException e) {
                e.printStackTrace();
            }finally {
                cd.countDown();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

       ExecutorService service = Executors.newFixedThreadPool(10);


        for (int i = 0; i < 10000; i++) {
            service.execute(new ParseDate(i));
        }

        cd.await();

        System.out.println("mission complete!");

        tl = null;

        System.gc();

        System.out.println("第一次执行gc完毕");

        //在设置ThreadLocal的时候，会清除ThreadLocal中的无效对象

//        tl = new ThreadLocal<SimpleDateFormat>();
//
//        cd = new CountDownLatch(10000);
//
//        for (int i = 0; i < 10000; i++) {
//            service.execute(new ParseDate(i));
//        }
//
//        cd.await();
//
//        Thread.sleep(1000);
//
//        System.gc();
//
//        System.out.println("第二次执行gc完毕");
    }


}
