package com.youguu.zhujie.useannotation;

import java.lang.reflect.Method;
import java.sql.Connection;

public class JdbcUtil {

    @JdbcInfo(url = "jdbc:mysql://localhost:3306/day16",password = "1234")
    public static Connection getConnection() throws NoSuchMethodException {
        //1.获取字节码文件

        //Class cl = JdbcUtil.class;

        JdbcUtil jdbcUtil = new JdbcUtil();

        Class cl = jdbcUtil.getClass();

        //2.获取getConnection
        Method m = cl.getMethod("getConnection",null);

        //3.判断该方法是否有jdbcInfo注解

        if(m.isAnnotationPresent(JdbcInfo.class)){

            //4.获取注解的四个属性
            JdbcInfo info = m.getAnnotation(JdbcInfo.class);

            String driverClass = info.driveClass();

            String password = info.password();

            System.out.println("driverClass:"+driverClass);

            System.out.println("password:"+password);
        }

        return null;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        JdbcUtil.getConnection();
    }

}
