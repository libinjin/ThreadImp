package com.youguu.listAndMap.arraylist;

import java.util.Arrays;

/**
 * 数组扩容
 */
public class ArrayCopy {

    public static void main(String[] args) {

        Object[] objects = new Object[2];

        objects[0] = "aaa";

        objects[1] = "bbb";


        objects = Arrays.copyOf(objects, 3);

        for (int i = 0; i < objects.length; i++) {
            System.out.println(objects[i]);
        }

        System.out.println("长度："+objects.length);


        Object[] newObejct = new Object[1];

        System.arraycopy(objects, 0, newObejct, 0,1);

        System.out.println(newObejct.length);
    }
}
