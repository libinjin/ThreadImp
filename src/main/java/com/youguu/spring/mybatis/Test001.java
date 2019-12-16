package com.youguu.spring.mybatis;

/**
 * 1.mapper接口的方法需要和SQL进行绑定
 * 思考：接口不能被实例化，那么如何实现调用
 * 1.UserMapper使用字节码技术创建虚拟子类
 * 2.使用匿名内部类
 * 3.使用动态代理创建代理对象
 */
public class Test001 {


}
