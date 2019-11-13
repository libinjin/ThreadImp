package com.youguu.spring.springIOC.xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class MyClassPathXmlApplicationContext {

    private String path;


    /**
     * ClassPathXmlApp("spring_02.xml");
     *
     * 1.获取xml
     *
     * 2.解析xml获取所有子element
     */
    public MyClassPathXmlApplicationContext(String path) {
        this.path = path;
    }

    public Object getBean(String beanId){

        try {
            //获取全部elements
            List<Element> elementList = genXML();

            //获取beanId的class全称
            String className = getClassName(elementList, beanId);

            if(null == className){
                throw new RuntimeException("找不到该beanId的类");
            }

            Class cls = Class.forName(className);

            Constructor cs =  cls.getDeclaredConstructor(String.class);

            cs.setAccessible(true);

            return cs.newInstance("libin");

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getClassName(List<Element> elementList, String beanId) {
        //找到Root下的bean
        for (Element element : elementList) {
            String nodeName = element.getName();
            if("bean".equals(nodeName)){
                List<Attribute> attributes = element.attributes();
                String beanIdValue = element.attributeValue("id");
                if(beanIdValue != null && beanId.equals(beanIdValue)){
                    String classValue = element.attributeValue("class");
                        if(classValue != null && !"".equals(classValue)){
                            return classValue;
                        }
                }
            }
        }
        return null;
    }

    private List<Element> genXML() throws DocumentException {

        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

        SAXReader saxReader = new SAXReader();

        Document document = saxReader.read(inputStream);

        Element rootElement = document.getRootElement();

        List<Element> elements =  rootElement.elements();

        return elements;
    }

    public static void main(String[] args) {
        MyClassPathXmlApplicationContext context = new MyClassPathXmlApplicationContext("spring_02.xml");
        UserServiceImpl userService = (UserServiceImpl) context.getBean("userService");
        String name = userService.getName();
        System.out.println(name);
        userService.add();
    }
}
