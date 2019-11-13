package com.youguu.threads.disruptor.consumer;

import com.lmax.disruptor.EventHandler;
import com.youguu.threads.disruptor.pojo.LongEvent;

/**
 * 消费者
 */
public class LongEventHandler2 implements EventHandler<LongEvent>  {


    @Override
    public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("消费者2："+longEvent.getValue());
    }
}
