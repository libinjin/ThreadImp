package com.youguu.test.hashMap;
/**
 * 手写一个HashMap
 * @author dell
 *
 */
public interface DNMap<K,V> {
	
   V put(K key, V value);
   
   V get(K key);
   
   int size();
   
   //内部接口
   interface Entry<K,V>{
	   K getKey();
	   
	   V getValue();
	   
   }
}
