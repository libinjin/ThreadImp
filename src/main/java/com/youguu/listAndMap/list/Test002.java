package com.youguu.listAndMap.list;

import java.util.ArrayList;

public class Test002 {

    public static void main(String[] args) {

       /* ArrayList<String> arrayList = new ArrayList<>();
        String str = arrayList.remove(1);*/
        //1、jdk1.7之后数组默认

        //elementData 存放数据的数组 Object[] elementData
        //在1.7之后，创建一个ArrayList是不会初始化elementData的，1.6默认构造函数初始化elementData数组的大小
    /*    ArrayList<String> arrayList = new ArrayList<>();

        JDKArrayList<Object> jdkArrayList = new JDKArrayList<>();
        jdkArrayList.add("张三");
*/

        //java的反射机制是不能拿到泛型的类型的
        //泛型只是在编译的时候才会有的，编译之后是没有的
        //泛型是在运行时才决定类型的
        MyArrayList<String> myArrayList = new MyArrayList<String>(3);

        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");
        myArrayList.add("d");
        myArrayList.add("e");
        System.out.println(myArrayList.get(1));
        myArrayList.remove(4);
        System.out.println(myArrayList);
    }
}
