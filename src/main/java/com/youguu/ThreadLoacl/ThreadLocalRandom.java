package com.youguu.ThreadLoacl;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadLocalRandom {


    public static final int GEN_COUNT = 100000000;

    public static final int THEAD_COUNT = 4;

    static ExecutorService exe = Executors.newFixedThreadPool(THEAD_COUNT);

    public static Random rnd = new Random(123);

    public static ThreadLocal<Random> tRnd = new ThreadLocal<Random>(){
        @Override
        protected Random initialValue() {
            return new Random(123);
        }
    };

    public static class RndTask implements Callable<Long>{

        private int mode = 0;

        public RndTask(int mode) {
            this.mode = mode;
        }

        public Random getRandom(){
            if(mode == 0){
                return rnd;
            }else if(mode == 1){
                return tRnd.get();
            }
            return null;
        }

        @Override
        public Long call() throws Exception {

            long b = System.currentTimeMillis();

            for (int i = 0; i < GEN_COUNT; i++) {
                getRandom().nextInt();
            }

            long e = System.currentTimeMillis();

            System.out.println(Thread.currentThread().getName()+"耗时："+(e-b)+"ms");
            return e-b;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Future<Long>[] future = new Future[THEAD_COUNT];

        for (int i = 0; i < THEAD_COUNT; i++) {
            System.out.println("执行："+i);
            future[i] = exe.submit(new RndTask(0));
        }

        long totalTime = 0;

        for (int i = 0; i < THEAD_COUNT; i++) {
            totalTime += future[i].get();
        }

        System.out.println("多线程公用一个Random产生随机数，共耗时"+totalTime+"ms");


        for (int i = 0; i < THEAD_COUNT; i++) {
            System.out.println("执行："+i);
            future[i] = exe.submit(new RndTask(1));
        }

        totalTime = 0;

        for (int i = 0; i < THEAD_COUNT; i++) {
            totalTime += future[i].get();
        }

        System.out.println("使用ThreadLocal包装Random时，产生随机数，共耗时"+totalTime+"ms");

        exe.shutdown();

    }
}
