package com.youguu.test.hashMap.impl;


import com.youguu.test.hashMap.DNMap;

import java.util.HashMap;

/**
 * K 和 V 等同于Object，它们是泛型
 * @author dell
 *
 * @param <K>
 * @param <V>
 */
public class DNHashMap<K,V> implements DNMap<K, V> {

	//数组的长度会定义为2的倍数。map的默认长度也是16
	private static Integer defaultLength=16;
	//定义一个负载因子
	private static double defaultLoad= 0.75;
	//定义一个数组是Entry类型的
	private Entry<K, V>[] table = new Entry[16];
	//记录数组元素个数的
	private int size = 0;

	public DNHashMap(){
		this(defaultLength, defaultLoad);
	}
	public DNHashMap(int defaultLength,double defaultLoad){
		this.defaultLength = defaultLength;
		this.defaultLoad = defaultLoad;
	}
	@Override
	public V put(K key, V value) {
		//根据key找对应的index（数组下标）
		//根据key找到下标，根据下标找到元素
		int index = this.getIndex(key);
		//根据这个下标判断该位置是否有数据
		Entry<K, V> e = table[index];
		if(null == e){
			table[index] = new Entry(key, value, null, index);
			size++;
		}else{
			//说明该下标下已经有元素了
			Entry newEntry = new Entry(key,value,e,index);
			table[index] = newEntry;
		}
		return table[index].getValue();
	}
	//自定义hash函数
	private int getIndex(K key){
		//m要取比数组长度小的最大质数。
		int m = this.defaultLength-1;
		//拿到key的hash值
		//index = key%m
		return key.hashCode()%m;
	} 
	@Override
	public V get(K key) {

		//根据key找到下标，根据下标找到元素
		int index = this.getIndex(key);
		//根据这个下标判断该位置是否有数据
		Entry<K,V> e = table[index];

		return table[index].getValue()==null?null:table[index].getValue();
	}

	@Override
	public int size() {
		return size;
	}
	class Entry<K,V> implements DNMap.Entry<K, V>{

		K key;
		V value;
		Entry<K,V> next;//指向下一个元素，有冲突时的指向
		int index;//记录Entry在数组中的位置

			Entry(K k,V v,Entry<K,V> nxt,int indx){
			key = k;
			value=v;
			next = nxt;
			index = indx;
		}
		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {

			return value;
		}
	}
    public static void main(String[] args) {
		DNHashMap map = new DNHashMap();
		HashMap map2;
		map.put("01", "Jack");
		map.put("02", "tom");
		System.out.println(map.get("01"));
	}
}
