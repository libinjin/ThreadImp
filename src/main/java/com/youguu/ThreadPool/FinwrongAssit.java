package com.youguu.ThreadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FinwrongAssit extends ThreadPoolExecutor {

    public FinwrongAssit(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public void execute(Runnable command) {
        super.execute(wrap(command, clientTrace(), Thread.currentThread().getName()));
    }

    private Exception clientTrace(){

        return new Exception("Client stack trace"+ Thread.currentThread().getId());
    }


    private Runnable wrap(final Runnable task, final Exception clientStack, String clientThreadName){

        return new Runnable(){

            @Override
            public void run() {
                try {
                    task.run();
                    System.out.println(clientThreadName);
                } catch (Exception e) {
                    clientTrace();
                    throw e;
                }
            }
        };
    }
}
