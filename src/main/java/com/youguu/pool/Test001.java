package com.youguu.pool;

import java.sql.Connection;
import java.util.List;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 2.1 初始化线程池
 * 3.1 调用getConnection方法  获得连接
 *    3.1.1 先去freeConnection获取当前连接，存放在activeConnection
 * 4.1 调用releaseConnection方法释放连接 --资源回收
 *     线程用完后，发现空闲线程没有满，就把线程交还给空闲线程
 *     而不用真正的回收清理掉线程
 *
 * 4.1.1 获取activeConnection集合的连接，转移到freeConnection集合中
 *
 */
public class Test001 {


    //使用线程安全的集合，空闲线程容器
    private List<Connection> freeConnection = new Vector<>();

    //活动线程 容器正在使用的连接
    private List<Connection> activeConnection = new Vector<>();


}
