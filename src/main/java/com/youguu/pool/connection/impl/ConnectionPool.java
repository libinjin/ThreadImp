package com.youguu.pool.connection.impl;

import com.youguu.pool.DbBean;
import com.youguu.pool.connection.IConnectionpool;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

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
public class ConnectionPool implements IConnectionpool {

    //使用线程安全的集合，空闲线程容器
    private List<Connection> freeConnection = new Vector<>();

    //活动线程 容器正在使用的连接 ,如果里面的线程太多了
    private List<Connection> activeConnection = new Vector<>();

    private DbBean dbBean;

    private int countDone;

    private AtomicInteger atomicCount = new AtomicInteger();

    public ConnectionPool(DbBean dbBean) {
        //
        this.dbBean = dbBean;
        init();
    }

    /**
     * 初始化线程池
     * 把初始化后得到的线程
     * 都放入到空闲线程池中
     */
    private void init(){
        if(dbBean == null){
            return;//最好抛出一个异常
        }

        //1.获取初始化连接数
        for (int i = 0; i < dbBean.getInitConnections(); i++) {
            //2.创建连接
            Connection connection = newConnection();
            if(connection != null){
                //3.存放在freeConnection集合
                freeConnection.add(connection);
            }
        }
    }

    /**
     * 新建连接
     * @return
     */
    private synchronized Connection newConnection(){
        try {

            Class.forName(dbBean.getDriverName());
            Connection connection = DriverManager.getConnection(dbBean.getUrl(), dbBean.getUserName(), dbBean.getPassword());
            //最好用原子类
            countDone++;
            atomicCount.incrementAndGet();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取连接，复用机制
     * @return
     */
    @Override
    public synchronized Connection getConnection() {
        try {
            //
            /**
             * 1.当前连接数大于最大连接数时，阻塞等待
             * 2.当前连接数小于最大连接数时，即可获得
             * 一个连接（要么是从空闲线程池中取，要么新建一个连接）
             *    2.1先试图从空闲线程池中拿去连接。
             *       如果有空闲线程，即可拿到连接。
             *    2.2如果空闲线程池中没有，再创建新的连接
             *
             */
            Connection connection = null;
            if(countDone < dbBean.getMaxActiveConnections()){

                if(freeConnection.size()>0){
                    //从空闲线程池中获取一个连接
                    connection = freeConnection.remove(0);
                }else{
                    //新建一个连接
                    connection = newConnection();
                }

                //判断线程是否可用，可用将线程
                if(isAvaliable(connection)){
                    activeConnection.add(connection);
                }else{
                    //不可用，重新获取
                    connection = getConnection();
                }

            }else{
                //大于最大活动连接数，应该等待1秒，然后继续尝试获取连接
                wait(dbBean.getConnTimeOut());
                getConnection();
            }
            return connection;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isAvaliable(Connection connection) throws SQLException {
        if(null == connection || connection.isClosed() ){
            return false;
        }
        return true;
    }


    /**
     * 释放连接（可回收机制）
     * @return
     */
    @Override
    public synchronized void releaseConnection(Connection connection) {
        /**
         * 如果空闲线程池没有满，归还给空闲线程池
         */
        try {
            if(isAvaliable(connection)){
                if(freeConnection.size() < dbBean.getMaxConnections()){
                    freeConnection.add(connection);
                }else{
                    //空线程池满了，直接关闭
                    connection.close();
                }
            }
            activeConnection.remove(connection);
            countDone--;
            //是否完了，如果有在等待那连接的，就叫醒，停止阻塞getConnection
            notifyAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ConnectionPool connectionPool = new ConnectionPool(new DbBean());

        System.out.println("获取连接");
        Connection connection = connectionPool.getConnection();

        System.out.println("释放连接");
        connectionPool.releaseConnection(connection);
    }
}
