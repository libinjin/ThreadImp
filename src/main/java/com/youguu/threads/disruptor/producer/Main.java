package com.youguu.threads.disruptor.producer;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.youguu.threads.disruptor.consumer.LongEventHandler;
import com.youguu.threads.disruptor.consumer.LongEventHandler2;
import com.youguu.threads.disruptor.factory.LongEventFactory;
import com.youguu.threads.disruptor.pojo.LongEvent;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {


        //1.创建一个可可缓存的线程池
        ExecutorService executor = Executors.newCachedThreadPool();

        EventFactory<LongEvent> eventFactory =  new LongEventFactory();

        //3.创建ringBuffer大小，必须是2的整数次方
        int ringBufferSize = 1024 * 1024;

        //4.创建Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(eventFactory, ringBufferSize, executor,
                ProducerType.MULTI, new YieldingWaitStrategy());

        //5.注册消费者
        disruptor.handleEventsWith(new LongEventHandler());

        //重复消费
        disruptor.handleEventsWith(new LongEventHandler2());

        System.out.println("启动Disruptor，开始执行");
        //6.启动
        disruptor.start();

        //7.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        //8.创建生产者
        LongEventProducer producer = new LongEventProducer(ringBuffer);


        //异步推送
        ByteBuffer buffer = ByteBuffer.allocate(8);
        for (int i = 0; i < 100; i++) {
            buffer.putLong(0, i);
            producer.onData(buffer);
        }
        executor.shutdown();
        disruptor.shutdown();
    }
}
