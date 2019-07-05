package com.youguu.itcast.proxy.impl;


import com.youguu.itcast.proxy.TargetInterface;

public class TargetInterfaceImp implements TargetInterface {

    @Override
    public void method() {
        System.out.println("method running...");
    }

    @Override
    public String method2() {
        System.out.println("method2 runing");
        return "method2";
    }

    @Override
    public int method3(int x) {
        return x;
    }
}
