package com.youguu.designModel.signton;

/**
 * 使用内部类创建单例
 */
public class InnerSingle {

    private InnerSingle(){
        System.out.println("创建InnerSingle对象");
    }

    //控制了对象创建时机，利用JVM的类初始化机制创建单例。
    private static class InnerSingleHolder{
        private static InnerSingle innerSingle = new InnerSingle();
    }

    public static InnerSingle getInstance(){
        return InnerSingleHolder.innerSingle;
    }
}
