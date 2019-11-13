package com.youguu.spring.springMVC.controller;

import com.youguu.spring.springMVC.annotation.ExController;
import com.youguu.spring.springMVC.annotation.ExRequestMapping;
import com.youguu.spring.util.ClassUtils;

@ExController
@ExRequestMapping("/ext")
public class ExIndexController {

    @ExRequestMapping("/test")
    public String test(){

        //ClassUtils.getPackageClass()
        System.out.println("手写SpringMVC框架...");
        return "index";
    }
}
