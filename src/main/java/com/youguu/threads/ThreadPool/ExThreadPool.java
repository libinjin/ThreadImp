package com.youguu.threads.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 对TheadPool的扩展
 * 监视每个线程在做的事情
 */
public class ExThreadPool {


    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = new ThreadPoolExecutor(5, 5, 0l, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(5)) {

            @Override
            protected void beforeExecute(Thread t, Runnable r) {

                System.out.println("准备执行任务："+((MyTask)r).name);

            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行任务："+((MyTask)r).name+"完毕");
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };


        for (int i = 0; i < 5; i++) {
            MyTask task = new MyTask("my-task-"+i, 100, i);
            service.execute(task);
            Thread.sleep(10);
        }
        service.shutdown();
    }

    public static class MyTask implements Runnable{

        private String name;
        int a,b;

        public MyTask(String name, int a, int b) {
            this.name = name;
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {

            System.out.println("当前正在执行任务的线程id是："+Thread.currentThread().getId()+",该任务是："+name);
            double re = a/b;
            System.out.println(Thread.currentThread().getId()+"执行任务的线程执行结果："+re);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
