package com.youguu.reflect.classLoader;

import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader{


    @Override
    protected Class<?> findClass(String name){
        InputStream ins = null;
        try {
            //1.获取文件名称
            String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
            //2.读取文件名称
            ins = this.getClass().getResourceAsStream(fileName);

            byte[] b = new byte[ins.available()];
            ins.read(b);
            //3.读取byte数组给JVM识别class对象
            return defineClass(name,b,0,b.length);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                ins.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
