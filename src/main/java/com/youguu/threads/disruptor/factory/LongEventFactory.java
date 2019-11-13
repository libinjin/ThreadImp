package com.youguu.threads.disruptor.factory;

import com.lmax.disruptor.EventFactory;
import com.youguu.threads.disruptor.pojo.LongEvent;

/**
 * 事件处理器简单地把
 * 事件中存储的数据打印到终端
 *
 * 生产者工厂
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {

        return new LongEvent();
    }
}
