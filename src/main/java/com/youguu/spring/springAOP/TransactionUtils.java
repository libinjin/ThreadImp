package com.youguu.spring.springAOP;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.annotation.Resource;

//用多例型的，因为线程安全问题
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TransactionUtils {

    private TransactionStatus transactionStatus;


    @Resource(name = "asteroidTX")
    private DataSourceTransactionManager transactionManager;

    //开启事务
    public TransactionStatus begin(){
        System.out.println("开启事务");
        //默认的传播级别
        transactionStatus = transactionManager.getTransaction(new DefaultTransactionAttribute());
        return transactionStatus;
    }

    //提交事务
    public void commit(TransactionStatus transaction){
        transactionManager.commit(transaction);
    }

    //回滚事务
    public void rollback(TransactionStatus transaction){
        transactionManager.rollback(transaction);
    }
}
