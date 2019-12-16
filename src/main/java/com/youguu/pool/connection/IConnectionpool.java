package com.youguu.pool.connection;

import java.sql.Connection;

public interface IConnectionpool {

    /**
     * 获取连接请求（重复利用机制）
     * @return
     */
    public Connection getConnection();

    /**
     * 释放连接（可回收机制）
     * @return
     */
    public void releaseConnection(Connection connection);
}
