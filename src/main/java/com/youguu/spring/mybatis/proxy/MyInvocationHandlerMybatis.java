package com.youguu.spring.mybatis.proxy;

import com.youguu.spring.mybatis.annotation.ExtInsert;
import com.youguu.spring.mybatis.annotation.ExtParam;
import com.youguu.spring.mybatis.annotation.ExtSelect;
import com.youguu.spring.mybatis.utils.JDBCUtils;
import com.youguu.spring.mybatis.utils.SQLUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 代理实现
 */
public class MyInvocationHandlerMybatis implements InvocationHandler {

    private Object object;

    public MyInvocationHandlerMybatis(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        /**
         * 1.判断方法上是否存在@ExInsert
         * 2.获取SQL语句
         * 3.将参数和SQL中位置进行匹配
         * 4.替换?
         * 5.调用jdbc底层代码执行语句
         */
        //1、
        ExtInsert extInsert = method.getDeclaredAnnotation(ExtInsert.class);
        if(extInsert != null) {
            return extInsert(extInsert, method, args);
        }

        ExtSelect extSelect = method.getDeclaredAnnotation(ExtSelect.class);
        if(extSelect != null){

            String sql = extSelect.value();

            //将参数和value放入到map中
            ConcurrentHashMap<String, Object> map = paramsMap(method, args);

            //把map中的值放入到list中 userId
            List<String> list = SQLUtils.sqlSelectParameter(sql);

            //根据SQL中的顺序做参数传递
            List<Object> sqlParams = new ArrayList<>();
            for (String key : list) {
                Object value = map.get(key);
                sqlParams.add(value);
            }

            //将SQL中的#{}替换成?
            String newSql = SQLUtils.parameQuestion(sql, list);

            ResultSet res = JDBCUtils.query(newSql, sqlParams);

            if(!res.next()){
                return null;
            }
            res.previous();
            //拿到返回类型
            Class<?> returnType = method.getReturnType();

            //实例化
            Object object = returnType.newInstance();

            while (res.next()){
                //获取返回值的所有属性
                Field[] fields = returnType.getDeclaredFields();

                for (Field field : fields) {
                    String fieldName = field.getName();
                    Object value = res.getObject(fieldName);
                    field.setAccessible(true);
                    field.set(object, value);
                }
            }
            return object;
        }
        return 1;
    }

    private int extInsert(ExtInsert extInsert, Method method, Object[] args) {

        String inserSql = extInsert.value();

        //定义Map<k, V>
        //      <@ExtParam, value>
        ConcurrentHashMap map = new ConcurrentHashMap<>();

        //args获取参数数组值
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];//拿到参数结果，但是还需要获取参数类型

            ExtParam extParam = parameter.getDeclaredAnnotation(ExtParam.class);
            if(extParam != null){
                String key = extParam.value();//参数名称
                Object value = args[i];
                System.out.println("key:"+key+","+"value:"+value);
                map.put(key, value);
            }
        }

        //存放sql执行参数
        List<Object> sqlparams = new ArrayList<>();
        //取出SQL中的#{userName}
        String[] strPara = SQLUtils.sqlInsertParameter(inserSql);
        for (String str:strPara) {
            Object paramValue = map.get(str);
            sqlparams.add(paramValue);
        }
        //将#{userName}替换成 ?
        String sql = SQLUtils.parameQuestion(inserSql, strPara);

        //4.替换参数变为?
        return JDBCUtils.insert(sql, false, sqlparams);
    }

    public ConcurrentHashMap<String, Object> paramsMap(Method method, Object[] args){

        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < args.length; i++) {
            //key
            ExtParam extParam = parameters[i].getDeclaredAnnotation(ExtParam.class);
            if(extParam != null){
                String key = extParam.value();
                Object value = args[i];
                map.put(key, value);
            }
        }
        return map;
    }
}
