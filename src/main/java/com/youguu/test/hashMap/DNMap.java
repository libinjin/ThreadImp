package com.youguu.test.hashMap;
/**
 * ��дһ��HashMap
 * @author dell
 *
 */
public interface DNMap<K,V> {
	
   V put(K key, V value);
   
   V get(K key);
   
   int size();
   
   //�ڲ��ӿ�
   interface Entry<K,V>{
	   K getKey();
	   
	   V getValue();
	   
   }
}
