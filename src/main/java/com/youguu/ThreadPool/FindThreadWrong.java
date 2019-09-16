package com.youguu.ThreadPool;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FindThreadWrong implements Runnable{

    String count;
    int a,b;

    public FindThreadWrong(int a, int b, String count) {
        this.count = count;
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        double re = a/b;
        System.out.println(re);
    }

    public static void main(String[] args) {

        //包装一个线程安全的hashMap
        //使用委托机制，将自己所有的Map相关的功能交给hashMap,自己负责线程安全
        Map map = Collections.synchronizedMap(new HashMap<>());



        ThreadPoolExecutor pools = new FinwrongAssit(0, Integer.MAX_VALUE, 0l, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());


       /* for (int i = 0; i < 5; i++) {
            pools.submit(new FindThreadWrong(100, i));
        }*/

        for (int i = 0; i < 5; i++) {
            System.out.println("第"+i+"次执行");
            pools.execute(new FindThreadWrong(100, i,"第"+i+"次执行"));
        }

        pools.shutdown();


    }
}
