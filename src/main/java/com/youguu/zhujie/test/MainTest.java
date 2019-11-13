package com.youguu.zhujie.test;


import com.youguu.zhujie.annotation.MyAnnotation1;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@MyAnnotation1("aa")
public class MainTest {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        //运行这个类的时候需要将测试类中带有@mytest所有方法执行

   /*     //1.获取字节码对象
        Class cl = MyTest.class;

        //获取所有的方法

        Method[] arr = cl.getMethods();
        //让方法执行
        for (int i = 0; i < arr.length; i++) {

            System.out.println(arr[i].getName());
        }*/

      /*  Method m2 = cl.getMethod("f1",java.lang.Integer.class);

        Method f1 = cl.getMethod("f2");

        f1.invoke(cl.newInstance());*/

        Class mainClass = MainTest.class;

        MyAnnotation1 MyAnnotation1 = (MyAnnotation1) mainClass.getAnnotation(MyAnnotation1.class);

        System.out.println(MyAnnotation1.value());

        Class cls = MyTest.class;

        Method[] methods = cls.getMethods();

        for (int i = 0; i < methods.length; i++) {
            Method methods1 = methods[i];
            if(methods1.isAnnotationPresent(MyTests.class)){

                MyTests myTests = methods1.getAnnotation(MyTests.class);

                String value = myTests.value();

                System.out.println(methods[i].getName()+"方法的注解value："+value);
            }

        }

        Class cl = MyTest.class;

        //
        Method[] arr = cl.getMethods();

        //让带有注解的方法执行
        for (Method m : arr){
            //判断方法是否有指定的@MyTests注解
            boolean flag = m.isAnnotationPresent(MyTests.class);
            if(flag){
                //System.out.println(m.getName());
                m.invoke(cl.newInstance());
            }
        }

    }

}
