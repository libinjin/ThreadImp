package com.youguu.threads.disruptor.producer;

import com.lmax.disruptor.RingBuffer;
import com.youguu.threads.disruptor.pojo.LongEvent;

import java.nio.ByteBuffer;

/**
 * 生产者
 */
public class LongEventProducer {

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer byteBuffer){

        //取到环形的位置
        long sequence = ringBuffer.next();

        //2.取出空的事件队列，
        LongEvent longEvent = ringBuffer.get(sequence);

        //给生产者赋值
        Long data = byteBuffer.getLong(0);

        //3.获取事件队列传递的数据
        longEvent.setValue(data);

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("producer send ready:");
            //往该队列中发送数据，代码要写到finally里面，防止报错导致阻塞
            ringBuffer.publish(sequence);
        }
    }

}
