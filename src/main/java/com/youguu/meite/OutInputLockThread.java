package com.youguu.meite;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OutInputLockThread {

    public static void main(String[] args) {


        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();

        queue.add(1);

        ResBean resBean = new ResBean();
        WriteLock write = new WriteLock(resBean);
        ReadLock read = new ReadLock(resBean);

        write.start();
        read.start();

    }
}
class ResBean{
    public boolean flag = true;
    public Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();
    public String name;
    public String sex;


}
/**
 * 生产者
 */
class WriteLock extends Thread{

    ResBean res;

    public WriteLock(ResBean res){
        this.res = res;
    }

    @Override
    public void run() {
        int count = 0;

        while (true){
            res.lock.lock();
            while (!res.flag){//flag为true时，正在写，false时在读
                try {
                    res.condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                if(count == 0){
                    res.name = "小红";
                    res.sex = "女";
                }else{
                    res.name = "于胜军";
                    res.sex = "男";
                }
                count = (count+1)%2;
                res.flag = false;
                res.condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                res.lock.unlock();
            }
        }
        //一人一次
    }
}

class ReadLock extends Thread{

    ResBean res;

    public ReadLock(ResBean res){
        this.res = res;
    }

    @Override
    public void run() {
        while (true){
            res.lock.lock();
            while (res.flag){//在写
                try {
                    res.condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            res.flag = true;
            res.condition.signal();
            try {
                System.out.println(res.name + ":" + res.sex);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                res.lock.unlock();
            }
        }
    }
}