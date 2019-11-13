package com.youguu.listAndMap.hashMap;

import java.util.HashMap;

public class ExtHashMap<K, V> implements ExtMap<K, V>{

    public static void main(String[] args) {
        // 基于什么原则 后进选出 非公平锁与公平锁
        ExtHashMap extHashMap = new ExtHashMap<String, String>();
        extHashMap.put("1号", "1号");// 0
        extHashMap.put("2号", "1号");// 1
        extHashMap.put("3号", "1号");// 2
        extHashMap.put("4号", "1号");// 3
        extHashMap.put("6号", "1号");// 4
        extHashMap.put("7号", "1号");
        extHashMap.put("14号", "1号");

        extHashMap.put("22号", "1号");
        extHashMap.put("26号", "1号");
        extHashMap.put("27号", "1号");
        extHashMap.put("28号", "1号");
        extHashMap.put("66号", "66");
        extHashMap.put("30号", "1号");
        System.out.println("扩容前数据....");
        extHashMap.print();
        System.out.println("扩容后数据....");
        extHashMap.put("31号", "1号");
        extHashMap.put("66号", "123466666");
        extHashMap.print();
        // 修改3号之后
        System.out.println(extHashMap.get("66号"));
    }

    //存储HashMap 数组元素 默认是没有初始化容器，懒加载的功能
    private Node<K, V>[] table;

    //实际用到的 存储容量大小
    int size;

    /**
     * 负载因子 0.75，在扩容的时候才会用到
     * 负载因子越小，hash冲突越少
     */
    float load_factor = 0.75f;

    //table默认初始大小16
    static int DEFAULT_INITIAL_CAPACITY = 16;

    @Override
    public V put(K k, V v) {
        //1.判断table是否为null,为null时初始化
        if(table == null){
            table = new Node[DEFAULT_INITIAL_CAPACITY];
        }
        //1.是否要扩容 hashMap，为什么要扩容
        //因为链表越长，查询效率越低，节点越多，会影响到效率
        /**
         * 2.扩容数组之后，有什么影响
         * hashCode相同时，但是扩容数组之后长度发生变化
         * 重新计算index，重新取模计算index
         */
        if(size >= DEFAULT_INITIAL_CAPACITY * load_factor){
            restSize();
        }

        //3.计算hash值指定下标位置
        int index = getIndex(k ,table.length);

        //封装一个Node
        //Node node = new Node()
        Node<K, V> node = table[index];

        if(node == null){
            //没有hash冲突的
            node = new Node<>(k, v, null);
            size++;
        }else{
            //直接添加冲突node到前面，而不是后面
            K key = node.getKey();
            if(key.equals(k) || key == k){//原来
                //hashCode相同，而且equals相等情况，说明是同一个对象，修改值
                //node.value = v;
                return node.setValue(v);
            }else{
                //如果没有key相同的，说明同一个hashCode取模数或者hashCode相同，但是不同的Key（对象不同）,hash冲突
                //把该值放到Node的next中
                node = new Node<>(k, v, node);
                size++;
            }
        }
        table[index] = node;
        return null;
    }

    //扩容 实际存储大小 = 负载因子 * 初始容量 = 0.75 * 16 = 12
    //如果size > 12的时候需要扩容数组大小是之前的2倍
    final void restSize(){
        //1.生成新的table是之前的2倍
        Node<K, V>[] newTable = new Node[DEFAULT_INITIAL_CAPACITY << 1];
        int newSize = newTable.length;
        //2.重新计算index索引，存放在新的table里面
        for(int i = 0;i<table.length; i++){

            Node<K, V> oldNode = table[i];

            while (oldNode != null){

                table[i] = null;//help GC

                K oldkey = oldNode.key;
                //V value = oldNode.value;
                int index = getIndex(oldkey, newSize);

                //存放之前的table，原来的node next
                Node<K,V> oldNext = oldNode.next;

                //如果index下标在新newTable发生相同的index时，以链表进行存储
                //原来的node的下一个是最新的（原来的node存放下新的node下一个）
                oldNode.next = newTable[index];
                //将之前的node赋值给
                newTable[index] = oldNode;
                //判断是否继续循环遍历
                oldNode = oldNext;
            }
        }
        //3.将新的table赋值给原来的table
        table = newTable;
        DEFAULT_INITIAL_CAPACITY = newTable.length;
        newTable = null;
    }

    public int getIndex(K k, int length){
        int hashCode = k.hashCode();
        return hashCode % length;
    }

    @Override
    public V get(K k) {
        int index = getIndex(k, table.length);

        Node<K, V> node = table[index];

        Node<K, V> node1 = getNode(node, k);

        return node1 == null ? null:node1.value;
    }

    public Node<K, V> getNode(Node<K, V> node, K k){
        while (node != null){
            if(node.getKey().equals(k)){
                return node;
            }
            node = node.next;
        }
        return node;
    }

    @Override
    public int size() {
        return size;
    }

    void print() {

        for (int i = 0; i < table.length; i++) {
            Node<K, V> node = table[i];
            System.out.print("下标位置[" + i + "]");
            while (node != null) {
                System.out.print("[ key:" + node.getKey() + ",value:" + node.getValue() + "]");
                node = node.next;
                // if (node.next != null) {
                // node = node.next;
                // } else {
                // // 结束循环
                // node = null;
                // }

            }
            System.out.println();
        }

    }

    class Node<K, V> implements Entry<K, V>{

        private int hashCode;

        private K key;

        private V value;

        //单链表只有下一个节点
        private Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        //
        @Override
        public V setValue(V value) {
            //新值做覆盖，返回老的值
            V olValue = this.value;
            this.value = value;
            return olValue;
        }
    }
}
