package com.youguu.meite.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class VectorDemo {

    public static void main(String[] args) {

        Vector vector = new Vector();
        //vector.get(1);

        ArrayBlockingQueue queue = new ArrayBlockingQueue(10);

        //int DEFAULT_CAPACITY = 10;
        List list = new ArrayList();

        //initialCapacity = 11，效率太低了
        Hashtable table = new Hashtable();

        table.put("","");

        HashMap map = new HashMap();

        Collections.synchronizedMap(map);

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(30);
        concurrentHashMap.put("1","one");

        //线程安全的ArrayList，内部由引用代替加锁，提升执行效率
        CopyOnWriteArrayList<String> copy = new CopyOnWriteArrayList();
        copy.add("s");

    }
}
