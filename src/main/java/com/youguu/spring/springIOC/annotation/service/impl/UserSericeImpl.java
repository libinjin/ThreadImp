package com.youguu.spring.springIOC.annotation.service.impl;

import com.youguu.spring.springIOC.annotation.ExResource;
import com.youguu.spring.springIOC.annotation.ExService;
import com.youguu.spring.springIOC.annotation.service.OrderService;
import com.youguu.spring.springIOC.annotation.service.UserSerice;

@ExService
public class UserSericeImpl implements UserSerice {


    @ExResource
    private OrderService orderServiceImpl;

    @Override
    public void add(){
        orderServiceImpl.addOrder();
        System.out.println("调用注解方法里面的UserSerice类中的add方法");
    }
}
