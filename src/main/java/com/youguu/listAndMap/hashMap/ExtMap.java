package com.youguu.listAndMap.hashMap;

public interface ExtMap<K, V> {

    public V put(K k, V v);

    public V get(K k);

    public int size();

    //Entry的作用 等于Node节点
    interface Entry<K, V>{
        K getKey();

        V getValue();

        V setValue(V value);
    }
}
