package com.youguu.threads.producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者
 */
public class ProducerThread extends Thread{
    private BlockingQueue queue;
    //其他线程可见
    private volatile boolean flag = true;
    private static AtomicInteger count = new AtomicInteger();

    public ProducerThread(BlockingQueue queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("生产者线程启动...");
        try {
            while (flag){
                String data = count.incrementAndGet()+"";
                System.out.println(Thread.currentThread().getName()+"正在生产的data："+data);
                //添加队列
                boolean offer = queue.offer(data);
                if(!offer){
                    System.out.println("添加队列失败");
                }else{
                    System.out.println("生产者添加"+data+"成功");
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("生产者线程停止");
        }
    }

    public void stopThread(){
        this.flag = false;
    }
}

class ConsumerThread extends Thread{

    private BlockingQueue queue;
    private volatile boolean flag = true;

    ConsumerThread(BlockingQueue queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            System.out.println("消费者在消费线程...");
            while (flag){
                //获取完毕后，队列会删除掉
                String data = (String) queue.poll(2, TimeUnit.SECONDS);

                if(data != null){
                    System.out.println("消费者获取data："+data);
                }else{
                    System.out.println("消费者获取data失败");
                    this.flag = false;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("消费者线程停止");
        }
    }
}

class Test006{

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue queue = new LinkedBlockingQueue(10);
        ProducerThread producer = new ProducerThread(queue);
        producer.start();

        ProducerThread producerThread2 = new ProducerThread(queue);
        producerThread2.start();

        ConsumerThread consumer = new ConsumerThread(queue);
        consumer.start();

        //生产者生产10秒后停止生产
        Thread.sleep(10 * 1000);
        producer.stop();
        producerThread2.stop();
    }
}