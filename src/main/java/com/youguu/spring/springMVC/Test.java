package com.youguu.spring.springMVC;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 1.创建一个前端控制器DispatcherServlet
 *   拦截所有请求，基于servlet
 *  2、初始化操作，重写init方法，
 *    2.1将扫包范围内所有的类，注入到SpringMVC容器中，存放在Map集合中，key为默认类名小写，value对象
 *    2.2 将URL映射到方法上
 *        判断类上是否有注解，使用java反射机制循环遍历，判断方法上是否存在注解，进行url和方法对应
 *
 *
 */
public class Test {

    //beanId类名  Object对象
    private ConcurrentHashMap<String, Object> springmvcBeans = new ConcurrentHashMap<>();

    //url映射地址 Object对象
    private ConcurrentHashMap<String, Object> urlBeans = new ConcurrentHashMap<>();

    //Object对象 方法名称
    private ConcurrentHashMap<String, Object> urlMethods = new ConcurrentHashMap<>();



}
