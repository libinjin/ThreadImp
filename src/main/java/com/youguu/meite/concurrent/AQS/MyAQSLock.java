package com.youguu.meite.concurrent.AQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyAQSLock implements Lock {

    private Helper helper = new Helper();


    private class Helper extends AbstractQueuedSynchronizer{

        @Override
        protected boolean tryAcquire(int arg) {
            /**
             * 1.第一个线程进来，可以拿到锁，因此我们可以返回true
             *
             * 2、如果第二个线程进来，则拿不到锁，返回false。
             * （如果当前进来的线程和当前保存的线程是同一个线程则可以拿到锁，但是要状态值+1）
             */
            // 3、如何判断是第一个线程
            int state = getState();
            Thread t = Thread.currentThread();
            //如果状态为0，就是第一个线程
            if(state == 0){
                if(compareAndSetState(0, arg)){
                    //设置当前线程为独占锁线程
                    setExclusiveOwnerThread(t);
                    return true;
                }
            }else if (getExclusiveOwnerThread() == t){
                setState(state + 1);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            //锁的获取和释放是一一对应的，那么低啊用此方法
            //的线程一定是当前线程
            //获取当前线程，如果不等于独占锁的线程
            if(Thread.currentThread() != getExclusiveOwnerThread()){
                throw new RuntimeException();
            }
            int state = getState() - arg;
            boolean flag = false;
            if(state == 0){
                setExclusiveOwnerThread(null);
                flag = true;
            }
            setState(state);
            return flag;
        }

        Condition newCondition(){
            return new ConditionObject();
        }
    }


    @Override
    public void lock() {
        helper.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        helper.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return helper.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return helper.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        helper.tryRelease(1);
    }

    @Override
    public Condition newCondition() {
        return helper.newCondition();
    }
}
