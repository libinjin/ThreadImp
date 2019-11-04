package com.youguu.listAndMap.list;

public class Test003 {

    public static void main(String[] args) {

        MyArrayList list = new MyArrayList();

        list.add("libin");
        list.add("jjwan");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }
}
