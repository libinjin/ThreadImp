package com.youguu.spring.springIOC.annotation.service.impl;

import com.youguu.spring.springIOC.annotation.ExService;
import com.youguu.spring.springIOC.annotation.service.OrderService;

@ExService
public class OrderServiceImpl implements OrderService {


    @Override
    public void addOrder() {
        System.out.println("addOrder");
    }
}
