package com.youguu.GC.JVMMemory;

public class RuntimeDemo {

    public static void main(String[] args) {

        byte[] b = new byte[25 * 1024 * 1024];
        System.out.println("分配了4M空间给数组");

        System.out.println("最大内存："+ Runtime.getRuntime().maxMemory()/1024/1024+"MB");
        System.out.println("可用内存："+Runtime.getRuntime().freeMemory()/1024/1024+"MB");
        System.out.println("已经使用内存："+Runtime.getRuntime().totalMemory()/1024/1024+"MB");
    }
}
