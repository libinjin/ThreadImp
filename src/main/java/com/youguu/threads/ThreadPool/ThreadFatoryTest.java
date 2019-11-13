package com.youguu.threads.ThreadPool;

import java.util.concurrent.ThreadFactory;

public class ThreadFatoryTest {

    public static void main(String[] args) {

        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("");
                t.setDaemon(false);
                return t;
            }
        };

       /* ExecutorService service = new ThreadPoolExecutor(5,5,0L, TimeUnit.SECONDS,new SynchronousQueue<>(), threadFactory);

        service.execute(new Thread(){
            @Override
            public void run() {
                System.out.println("aa");
            }
        });*/
    }
}
