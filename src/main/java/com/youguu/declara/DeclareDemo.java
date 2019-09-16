package com.youguu.declara;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 声明式函数式编程
 */
public class DeclareDemo {

    public static void main(String[] args) {
/*
        int[] arr = {1,3,4,5,6,7,7};

        Arrays.stream(arr).forEach(System.out::println);

        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        list.forEach(System.out::println);*/

        System.out.println("开始：");
        test();
    }

    public static void test(){
        //不变的对象
        int[] arr = {1,3,5,7};

        Arrays.stream(arr).map((x) ->x+1).forEach(System.out::println);

        //没有发生变化不变模式
        for (int i = 0; i < arr.length; i++) {
            System.out.println("真实的："+arr[i]);
        }
    }
}
