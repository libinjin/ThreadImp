package com.youguu.spring.BeanLife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class UserEntity implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, BeanPostProcessor, DisposableBean {

    private String userName;

    private Integer age = null;

    public UserEntity() {
        System.out.println("1.无参构造函数...");
    }

    public UserEntity(String userName, Integer age) {
        System.out.println("我是有参构造函数userName:"+userName+",age:"+age);
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        System.out.println("2.设置userName，属性的注入");
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("3.setBeanName:"+name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("4.set BeanFactory");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("5.调用Bean的上下文,setApplicatinContext");
    }

    /**
     * 表示Bean的提交进度，可以拿到Bean到底有没有初始化完毕，
     * 还是结束
     * @param o
     * @param s
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {


        System.out.println("beanName:"+beanName+"初始化开始");
        System.out.println("bean:"+bean+"初始化开始");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("beanName:"+beanName+"初始化结束");
        System.out.println("bean:"+bean+"初始化开始");
        return bean;
    }

    @Override
    public void destroy() throws Exception {
        //判断Bean是否被销毁
        System.out.println("销毁bean");
    }
}
