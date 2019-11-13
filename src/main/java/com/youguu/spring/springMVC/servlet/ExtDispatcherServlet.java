package com.youguu.spring.springMVC.servlet;

import com.youguu.spring.springMVC.annotation.ExController;
import com.youguu.spring.springMVC.annotation.ExRequestMapping;
import com.youguu.spring.util.ClassUtils;
import org.springframework.util.StringUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 1.创建一个前端控制器DispatcherServlet
 *   拦截所有请求，基于servlet
 *  2、初始化操作，重写init方法，
 *    2.1将扫包范围内所有的类，注入到SpringMVC容器中，存放在Map集合中，key为默认类名小写，value对象
 *    2.2 将URL映射到方法上
 *        判断类上是否有注解，使用java反射机制循环遍历，判断方法上是否存在注解，进行url和方法对应
 */
public class ExtDispatcherServlet/* extends HttspServlet*/ {

    //beanId类名  Object对象
    private ConcurrentHashMap<String, Object> springmvcBeans = new ConcurrentHashMap<>();

    //url映射地址 Object对象
    private ConcurrentHashMap<String, Object> urlBeans = new ConcurrentHashMap<>();

    //url映射地址 方法名称
    private ConcurrentHashMap<String, String> urlMethods = new ConcurrentHashMap<>();

    //@Override
    public void init() throws ServletException {
        //1.获取当前包下的所有类
        List<Class<?>> classList = ClassUtils.getClasses("springMVC.controller");

        try {
            //判断类上是否有加注解 2.1将扫包范围内所有的类，注入到SpringMVC容器中，存放在Map集合中，key为默认类名小写，value对象
            springmvcBeans = initBean(classList);

            //3.将url映射和方法关联
            handlerMapping();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void handlerMapping() {

        //1.判断类上是否有url映射注解
        for (Map.Entry<String, Object> entry : springmvcBeans.entrySet()) {

            Object object  = entry.getValue();

            Class<?> cls = object.getClass();

            ExRequestMapping exRequestMapping = cls.getAnnotation(ExRequestMapping.class);
            String baseUrl = null;
            if(exRequestMapping != null){
                baseUrl = exRequestMapping.value();
            }

            //判断方法上是否加RequestMapping

            Method[] methods = cls.getDeclaredMethods();

            for (Method method : methods) {

                ExRequestMapping requestMapping =  method.getAnnotation(ExRequestMapping.class);

                if(requestMapping != null){
                    String beanUrl = requestMapping.value();
                    String methodUrl = "/springweb01"+baseUrl + beanUrl;

                    urlBeans.put(methodUrl, object);
                    urlMethods.put(methodUrl, method.getName());
                }
            }
        }

    }

    // 初始化bean对象
    public ConcurrentHashMap<String, Object> initBean(List<Class<?>> listClassesAnnotation)
            throws InstantiationException, IllegalAccessException {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<String, Object>();
        for (Class<?> classInfo : listClassesAnnotation) {
            if(classInfo.getAnnotation(ExController.class) != null){
                // 初始化对象
                Object newInstance = classInfo.newInstance();
                // 获取父类名称
                String beanId = ClassUtils.toLowerCaseFirstOne(classInfo.getSimpleName());
                concurrentHashMap.put(beanId, newInstance);
            }
        }
        return concurrentHashMap;
    }



    //@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    //@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1、获取请求url地址
        String uri =  req.getRequestURI();

        System.out.println("url:"+req.getRequestURL());

        if(StringUtils.isEmpty(uri)){
            return;
        }
        Object obejct = methodInvoke(uri);
        String result = obejct == null?"请检查路径是否正确": (String) obejct;

        //5、调用视图转换器
        String prefix = "/";
        String suffix = ".jsp";
        req.getRequestDispatcher(prefix+result+suffix).forward(req, resp);
      /*  resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(result);*/
    }

    public Object methodInvoke(String uri){
        Object object = urlBeans.get(uri);
        if(object == null){
            return null;
        }

        String methodName = urlMethods.get(uri);

        Class cls = object.getClass();

        try {
            Method method = cls.getDeclaredMethod(methodName);

            return method.invoke(object);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }
}
