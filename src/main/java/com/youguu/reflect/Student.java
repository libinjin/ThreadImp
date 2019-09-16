package com.youguu.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 反射测试
 */
public class Student {

    private int no;

    public String name;

    public Student() {
        System.out.println("无参构造器");
    }

    public Student(int no, String name) {
        this.no = no;
        this.name = name;
        System.out.println("带参构造器："+no+":"+name);
    }

    public void func(){
        System.out.println("无参方法：function");
    }

    public void show(String name, int no){
        System.out.println("no:"+no+" ,name:"+name);
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //通过字节码文件对应的堆中的对象

        //方式一：
        Class<Student> c1 = Student.class;

        Class c2 = new Student().getClass();

        //方式二：直接输入某个类名即可
        Class cls = Class.forName("com.youguu.reflect.Student");

        Field[] fields = cls.getFields();

        //私有的属性也可以获取到
        Field[] declarFileds = cls.getDeclaredFields();

 /*       for (int i = 0; i < declarFileds.length; i++) {
            System.out.println(declarFileds[i].getName()+"属性type："+declarFileds[i].getType());

            int modif = declarFileds[i].getModifiers();//访问权限

            System.out.println(Modifier.toString(modif));
        }

        Field name = cls.getDeclaredField("name");

        Field no = cls.getDeclaredField("no");

        Object object = cls.newInstance();

        name.set(object, "libin");

        System.out.println(name.get(object));

        no.setAccessible(true);//运行访问私有权限
        no.set(object, 1);

        System.out.println(no.get(object));*/

        Method[] methods = cls.getDeclaredMethods();
 /*       for (Method method:methods) {

            System.out.println("Methodname:"+method.getName());

            System.out.println(method.getReturnType());

            //返回参数列表
            System.out.println(method.getParameterTypes());
        }*/

        Object object = cls.newInstance();

        Method m1 = cls.getMethod("func");

        m1.invoke(object, null);

        Method m2 = cls.getMethod("show", java.lang.String.class, int.class);
        System.out.println(m2.invoke(object,"hello", 5));


        //获取构造器

        Constructor<?>[] crs = cls.getDeclaredConstructors();

        for (Constructor cr:crs) {
            System.out.println(Arrays.toString(cr.getParameterTypes()));
        }

        Constructor cr1 = cls.getDeclaredConstructor();

        cr1.newInstance();//调用无参

        Constructor cr2 = cls.getDeclaredConstructor(int.class, String.class);

        cr2.newInstance(1, "sir Li");//调用有参
    }

}
