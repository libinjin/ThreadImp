package com.youguu.spring.springIOC.xml;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class UserServiceImpl {

    public UserServiceImpl() {
    }

    /**
     * private构造方法是不能通过new创建对象的
     */
    private UserServiceImpl(String name){
        this.name = name;
        System.out.println("通过反射创建出来的对象");
    }

    private String name;

    private String pwd;

    public void add(){
        System.out.println("调用add方法");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public static void main(String[] args) throws DocumentException, SAXException {

        SAXReader reader = new SAXReader();

        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("student.xml");

        Document document = reader.read(inputStream);

        Element root = document.getRootElement();
        getNodes(root);

    }

    public static void getNodes(Element element){

        //获取全部属性
        List<Attribute> attributeList = element.attributes();

        for (Attribute attribute : attributeList) {
            System.out.println("属性名称："+attribute.getName()+",属性内容："+attribute.getText());
        }

        String value = element.getTextTrim();
        if (!StringUtils.isEmpty(value)) {
            System.out.println("name："+element.getName()+"value:"+ value);
        }

        //继续找子节点
        Iterator<Element> iterator = element.elementIterator();
        while (iterator.hasNext()){
            Element node = iterator.next();
            getNodes(node);
        }
    }

}
