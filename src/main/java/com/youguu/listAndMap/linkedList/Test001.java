package com.youguu.listAndMap.linkedList;


import java.util.LinkedList;

public class Test001 {

    /**
     * transient int size = 0;实际存储大小
     * transient Node<E> first;//第一个元素
     * transient Node<E> last;//最后一个元素
     *
     *
     * @param args
     */
    public static void main(String[] args) {

        LinkedList linkedList = new LinkedList();

        MyLinkedList<String> myLinkedList = new MyLinkedList<String>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");
        myLinkedList.add("e");

        String second = myLinkedList.get(0);

        System.out.println(second);
    }

}
