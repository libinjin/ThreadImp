package com.youguu.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Demo {

    public static void main(String[] args) {
        try {
            Class<?> forName = Class.forName("com.youguu.reflect.Demo");

            Object newInstance = forName.newInstance();

            Method method = forName.getDeclaredMethod("sum", int.class, int.class);

            int sum = (int) method.invoke(newInstance, 1,2);

            System.out.println(sum);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public int sum(int a, int b){
        return a+b;
    }
}
