package com.youguu.meite;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 生产者与消费者
 */
public class OutInputThread {

    public static void main(String[] args) {
        Res res = new Res();
        Write out = new Write(res);
        Read read = new Read(res);
        out.start();
        read.start();
    }
}
class Res{
    public boolean flag = true;
    public Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();
    public String name;
    public String sex;


}
/**
 * 生产者
 */
class Write extends Thread{

    Res res;

    public Write(Res res){
        this.res = res;
    }

    @Override
    public void run() {
        int count = 0;

        while (true){
            synchronized (res){

                while (!res.flag){//flag为true时，正在写，false时在读
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if(count == 0){
                    res.name = "小红";
                    res.sex = "女";
                }else{
                    res.name = "于胜军";
                    res.sex = "男";
                }
                res.flag = false;
                res.notify();
                count = (count+1)%2;
            }
            //一人一次
        }
    }
}

class Read extends Thread{

    Res res;

    public Read(Res res){
        this.res = res;
    }

    @Override
    public void run() {
        while (true){
            synchronized (res) {
                while (res.flag){//在写
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(res.name + ":" + res.sex);
                res.flag = true;
                res.notify();
            }
        }
    }
}