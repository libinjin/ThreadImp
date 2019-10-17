package com.youguu.meite.concurrent;

/**
 * 通过JDK自带的javap命令查看
 * SynchronizedDemo类的相关字节码信息
 * 首先切到该路径下，用javac SynchronizedDemo.java
 * 生成.class文件，然后执行javap -c -s -v -l SynchronizedDemo.class
 *
 */
public class SynchronizedDemo {

    public synchronized void method() {
        System.out.println("synchronized 方法");
    }

    public void synMethod(){

        synchronized (this){
            System.out.println("synchronized块中的方法");
        }
    }
}
