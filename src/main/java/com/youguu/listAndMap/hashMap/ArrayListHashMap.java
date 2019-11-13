package com.youguu.listAndMap.hashMap;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于ArrayList实现hashMap集合
 * 性能特别低
 */
public class ArrayListHashMap<Key, Value> {

    //map集合存储容量
    private List<Entry<Key, Value>> tables = new ArrayList<Entry<Key, Value>>();

    //map容器的实际容量
    private int size;

    public void put(Key key, Value value){
       //1.定义map容器
        //2.调用put时候，将该hash存储对象存到ArrayList中
       Entry entry = getEntry(key);
        if(entry != null){
            entry.value = value;
        }else{
            Entry newEntry = new Entry(key, value);
            tables.add(newEntry);
        }
    }

    public Value get(Key key){
        Entry entry = getEntry(key);
        return entry == null?null: (Value) entry.value;
    }

    public Entry getEntry(Key key){
        for (Entry<Key, Value> entry:tables) {
            if(entry.key.equals(key)){
                return entry;
            }
        }
        return null;
    }
}

//hashMap存储对象
class Entry<Key, Value>{

    Key key;
    Value value;

    public Entry(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public static void main(String[] args) {

        ArrayListHashMap map = new ArrayListHashMap();
        map.put("a","士大夫");
        map.put("b","getB");
        map.put("a","撒地方");
        System.out.println(map.get("a"));
    }
}
