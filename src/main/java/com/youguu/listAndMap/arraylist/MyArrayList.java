package com.youguu.listAndMap.arraylist;

import java.util.Arrays;

public class MyArrayList<E> {

    //ArrayList底层采用数组存放
    private Object[] elementData;

    //elementData中实际存放的大小,ArrayList中实际数据的数量
    private int size;

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENTDATA = {};

    //ArrayList数组初始的容量
    public MyArrayList(int initialCapacity) {
        if(initialCapacity > 0){
            this.elementData = new Object[initialCapacity];
        }else if(initialCapacity == 0){
            this.elementData = EMPTY_ELEMENTDATA;
        }else{
            throw new IllegalArgumentException("初始容量不合规: " + initialCapacity);
        }
    }

    //1.6及之前的会在初始化时就分配容量，之后在add的时候才分配容量
    public MyArrayList(){
        this(DEFAULT_CAPACITY);
    }

    //新增
    public void add(E e){
        //1判断实际存放的数组容量是否大于 elementData的容量
        //看是否需要扩容
        int minCapacity = size+1;//最小容量
        if(minCapacity > elementData.length){
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >>1);
            //扩容
            if (newCapacity - minCapacity < 0)
                newCapacity = minCapacity; // 最少保证容量和minCapacity一样
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
        //2存放
        elementData[size++] = e;
    }


    //删除
    public void remove(int index){
        Object object = get(index);
        //用 [0,1,2,3,4]  remove(2)
        int numMoved = size-index-1;
        if(numMoved >0){
            System.arraycopy(elementData, index, elementData, index+1, numMoved);
        }
        --size;//最后一个元素是重复的，需要删除
        elementData[size] = null;
    }



    //取出
    public Object get(int index){
        rangCheck(index);
        return elementData[index];
    }

    private void rangCheck(int index){
        if(index >= size){
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public int size(){
        return size;
    }

}
