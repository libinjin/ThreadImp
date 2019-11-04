package com.youguu.reflect.classLoader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 热部署
 */
public class HotSwap {


    public static void main(String[] args) throws InterruptedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        /*System.out.println("开始加载user1.0版本");
        User user1= new User();
        user1.add();
        //将user2.0版本class文件覆盖user1.0版本class文件
        Thread.sleep(10 * 1000);
        System.out.println("开始紧挨着user2.0版本");
        User user2 = new User();
        user2.add();*/
        loadUser();
        //需要被修改的class文件
        File file = new File("D:\\user\\User.class");

        //之间的class文件删除
        File file2 = new File("D:\\jar\\ThreadImp\\out\\production\\classes\\com\\youguu\\reflect\\classLoader\\User.class");

        if (!file2.delete()){
            System.out.println("热部署失败");
            return;
        }else{
            System.out.println("热部署成功");
        }
            file.renameTo(file2);//移动到
        Thread.sleep(10 * 1000);
        loadUser();
    }

    public static void loadUser() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader myClassLoader = new MyClassLoader();

        Class<?> findClass = myClassLoader.findClass("com.youguu.reflect.classLoader.User");

        Object user = findClass.newInstance();

        Method method = findClass.getMethod("add");

        method.invoke(user);

        System.out.println("class:"+user.getClass());

        System.out.println("classLoader:"+user.getClass().getClassLoader());
    }

}
