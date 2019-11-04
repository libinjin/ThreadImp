package com.youguu.GC;

public class GCDemo {

    public static void main(String[] args) {

        GCDemo gcDemo = new GCDemo();
        gcDemo = null;//不可达对象，没有继续被引用到的对象，需要回收
        //没有继续被使用的对象
//        Object object2 = object1;
//        object2 = null;

        //提示GC进行回收垃圾，不能立即回收垃圾的
        System.gc();
    }

    /**
     * 垃圾回收机制之前会执行的方法
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println("垃圾回收机制要开始执行我的方法");
    }
}
