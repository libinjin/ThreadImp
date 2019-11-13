package com.youguu.listAndMap.hashMap;

import java.util.LinkedList;

/**
 * 基于linkedList实现hashmap集合
 * jdk1.7的时候，hashmap使用数组+链表方式实现
 */
public class LinkedListHashMap {

    //先定义一个数组

    //Map存放元素信息
    LinkedList<Entry>[] tables = new LinkedList[998];


    /**
     * 如果hashCode相同的对象存放在同一个集合里面
     * @param key
     * @param value
     */
    public void put(Object key, Object value){
        Entry newEntry = new Entry(key, value);
        int hashCode = key.hashCode();
        System.out.println("hashCode:"+hashCode);
        //两个对象做比较，如果hashCode相同，对象的值不一定相同。
        //如果equals比较相同，hashCode一定相同

        /**
         * hash算法，根据hashCode值取模得到余数，
         * 取模后范围在998以内
         */
        int hash = hashCode % tables.length;

        LinkedList<Entry> entryLinkedList = tables[hash];

        if(entryLinkedList == null){
            //没有hash冲突的
            entryLinkedList = new LinkedList<Entry>();

            entryLinkedList.add(newEntry);

            tables[hash] = entryLinkedList;
        }else{
            //发生hash冲突，直接在链表后面添加node节点
            for (Entry entry:entryLinkedList) {
                if(entry.key.equals(key)){
                    //equals相同，hashCode一定相同
                    entry.value = value;
                }else{
                    //hashCode相同，对象的值不一定相同
                    //发送hash冲突问题，直接在后面继续添加链表节点
                    entryLinkedList.add(newEntry);
                }
            }
        }
    }

    //查询直接使用hash值直接定位在数组那个位置
    public Object get(Object key){
        int hashCode = key.hashCode();
        int hash = hashCode % tables.length;
        LinkedList<Entry> linkedLists = tables[hash];
        if(linkedLists != null){
            for (Entry entry:linkedLists) {
                if(entry.key.equals(key)){
                    return entry.value;
                }
            }
        }
        return null;
    }

    private class Entry<Key, Value>{

        Key key;
        Value value;

        public Entry(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {

        LinkedListHashMap map = new LinkedListHashMap();
        System.out.println(map.get("c"));
        map.put("a","libn");//hashCode参考ACSII码
        map.put("b","sd");

        map.put("a","jinwa");

        System.out.println(map.get("a"));

    }
}

