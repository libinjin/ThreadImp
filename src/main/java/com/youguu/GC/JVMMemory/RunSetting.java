package com.youguu.GC.JVMMemory;

public class RunSetting {

    //-Xms20m -Xmx20m -Xmn1m -xx:SurvivorRatio=2 -XX:PrintGCDetails -XX:+UseSerialGC
    public static void main(String[] args) {
        
        byte[] b = null;
        for (int i = 0; i < 10; i++) {
            b = new byte[1 * 1024 * 1024];
        }
        //SurvivorRatio=2
        System.out.println("最大内存："+ Runtime.getRuntime().maxMemory()/1024/1024+"MB");
        System.out.println("可用内存："+Runtime.getRuntime().freeMemory()/1024/1024+"MB");
        System.out.println("已经使用内存："+Runtime.getRuntime().totalMemory()/1024/1024+"MB");
    }
}
