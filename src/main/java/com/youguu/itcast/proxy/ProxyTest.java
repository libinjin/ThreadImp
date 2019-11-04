package com.youguu.itcast.proxy;



import com.youguu.itcast.proxy.impl.TargetInterfaceImp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK的Proxy方法实现的动态代理，目标对象必须有接口，没有接口不能实现jdk版的
 * 动态代理
 */
public class ProxyTest {



    public void test1(){

    }

    public static void main(String[] args) {
        //获得动态代理

        //TargetInterfaceImp.class;

        //方式一：
        TargetInterface targetInterface =
                (TargetInterface) Proxy.newProxyInstance(
                        TargetInterface.class.getClassLoader(),//与目标对象相同的类加载器
                        new Class[]{TargetInterface.class},//要接口的字节码对象
                        new InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                System.out.println("目标方法执行前的逻辑");
                                //obj是执行完目标方法返回的值
                                Object obj = method.invoke(new TargetInterfaceImp(), args);
                                return obj;
                            }
                        }//所要执行的
                );

//        targetInterface.method();
        String str = targetInterface.method2();
        int me = targetInterface.method3(8);
        System.out.println("li:"+str);
        System.out.println("li:"+me);

        //方式二：
        final TargetInterfaceImp target = new TargetInterfaceImp();
        TargetInterface proxy = (TargetInterface) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    //代理对象使用接口方法就是调用invoke
                    /**
                     * proxy是代理对象，下面的proxy引用了上面的Proxy对象
                     * 尽量不要用下面的proxy，用外面的proxy
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        /**
                         * method：代表是目标方法的字节码对象
                         * args：代表的是读取目标方法时参数
                         * 反射执行
                         */
                        //Object[] args = new Object[]{50}; 这里的args可以修改实际传入的参数
                        method.getName();//获得目标对象的名称
                        System.out.println("method.getName():"+method.getName());
                        Object object = method.invoke(target, args);
                        return object;
                    }
                }
        );
        proxy.method2();//调用匿名函数中的invoke，是目标对象的method1方法args
        int a = proxy.method3(8);
        System.out.println("a:"+a);

    }
}
