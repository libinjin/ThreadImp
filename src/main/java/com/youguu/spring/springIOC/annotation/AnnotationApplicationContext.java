package com.youguu.spring.springIOC.annotation;

import com.youguu.spring.springIOC.annotation.service.UserSerice;
import com.youguu.spring.util.ClassUtils;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 手写注解版springIOC
 */
public class AnnotationApplicationContext {

    public static void main(String[] args) {
        AnnotationApplicationContext applicationContext = new AnnotationApplicationContext("com.youguu");

        UserSerice userSerice = (UserSerice) applicationContext.getBean("UserSericeImpl");
        userSerice.add();
    }

    //扫包的范围
    private String packageName;

    //
    private ConcurrentHashMap<String, Object> beans;
    //传入需要扫包的包名称
    public AnnotationApplicationContext(String packageName){
        this.packageName = packageName;
    }

    //初始化对象
    public Object getBean(String beanId){
        Object object = null;

        try {
            //1、使用java的反射机制扫包，获取当前包下所有的类
            Set<Class<?>> classSet = ClassUtils.getPackageClass(packageName, ExService.class, true);

            //2、判断类上是否存在注入bean的注解
            if (classSet == null || classSet.isEmpty()) {
                throw new Exception("没有需要初始化的bean");
            }

            //类名称  对象
            beans = initBean(classSet);


            //3、使用beanID查找查找对应bean对象
            object = beans.get(toLowerCaseFirstOne(beanId));

            //4.使用反射去取类的属性，给依赖的属性赋值
            attriAssign(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    // 依赖注入注解原理
    public void attriAssign(Object object) throws IllegalAccessException {

        //1、使用反射机制，获取当前类的所有属性
        Class classInfo = object.getClass();

        Field[] fields = classInfo.getDeclaredFields();
        for (Field field : fields) {

            ExResource exResource = field.getAnnotation(ExResource.class);
            if(exResource != null){

                //获取属性名称
                String fieldName = field.getName();
                //从Bean容器中找出该对象
                Object bean = beans.get(fieldName);

                if(bean != null){
                    field.setAccessible(true);

                    //3、默认使用属性名称，查找bean容器对象，
                    //1、当前对象 2、参数给属性赋值
                    field.set(object, bean);
                }
            }
        }

    }



    // 初始化bean对象
    public ConcurrentHashMap<String, Object> initBean(Set<Class<?>> listClassesAnnotation)
            throws InstantiationException, IllegalAccessException {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<String, Object>();
        for (Class classInfo : listClassesAnnotation) {
            // 初始化对象
            Object newInstance = classInfo.newInstance();
            // 获取父类名称
            String beanId = toLowerCaseFirstOne(classInfo.getSimpleName());
            concurrentHashMap.put(beanId, newInstance);
        }
        return concurrentHashMap;
    }
    // 首字母转小写
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

}
