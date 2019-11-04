package com.youguu.GC.JVMMemory;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryDemo {

    public static void main(String[] args) {

        List<Object> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            byte[] b = new byte[1 * 1024 * 1024];
            list.add(b);
        }
        System.out.println("剩余："+Runtime.getRuntime().freeMemory()/1024/1024+"M");
        System.out.println("已经使用："+Runtime.getRuntime().totalMemory()/1024/1024+"M");
        System.out.println("最大："+Runtime.getRuntime().maxMemory()/1024/1024+"M");

    }
}
