package com.youguu.threads.test.hashMap.impl;


import com.youguu.threads.test.hashMap.DNMap;

import java.util.HashMap;

/**
 * K �� V ��ͬ��Object�������Ƿ���
 * @author dell
 *
 * @param <K>
 * @param <V>
 */
public class DNHashMap<K,V> implements DNMap<K, V> {

	//����ĳ��Ȼᶨ��Ϊ2�ı�����map��Ĭ�ϳ���Ҳ��16
	private static Integer defaultLength=16;
	//����һ����������
	private static double defaultLoad= 0.75;
	//����һ��������Entry���͵�
	private Entry<K, V>[] table = new Entry[16];
	//��¼����Ԫ�ظ�����
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
		//����key�Ҷ�Ӧ��index�������±꣩
		//����key�ҵ��±꣬�����±��ҵ�Ԫ��
		int index = this.getIndex(key);
		//��������±��жϸ�λ���Ƿ�������
		Entry<K, V> e = table[index];
		if(null == e){
			table[index] = new Entry(key, value, null, index);
			size++;
		}else{
			//˵�����±����Ѿ���Ԫ����
			Entry newEntry = new Entry(key,value,e,index);
			table[index] = newEntry;
		}
		return table[index].getValue();
	}
	//�Զ���hash����
	private int getIndex(K key){
		//mҪȡ�����鳤��С�����������
		int m = this.defaultLength-1;
		//�õ�key��hashֵ
		//index = key%m
		return key.hashCode()%m;
	} 
	@Override
	public V get(K key) {

		//����key�ҵ��±꣬�����±��ҵ�Ԫ��
		int index = this.getIndex(key);
		//��������±��жϸ�λ���Ƿ�������
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
		Entry<K,V> next;//ָ����һ��Ԫ�أ��г�ͻʱ��ָ��
		int index;//��¼Entry�������е�λ��

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
