package com.youguu.GC.JVMMemory;

public class StackOverMemeryDemo {

    private static int count;

    //最大深度：12322
             //274608 -Xss5m 设置最大递归调用次数  线程栈大小
    public static void count(){
        try {
            count++;
            count();
        } catch (Throwable e) {
            System.out.println("最大递归调用深度："+count);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        count();
    }
}
