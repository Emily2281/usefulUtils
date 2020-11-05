package com.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 自定义注解
 */
public class AnnotationMain {

    public static void main(String[] args){
        //1.找类
        Class clazz = Person.class;
        //2.找私有属性
        try {
            Field field = clazz.getDeclaredField("name");
            //3.获取属性上的注解对象
            MyAnnotation annotation = field.getAnnotation(MyAnnotation.class);
            //4.用反射执行注解类中的方法
            Class aclazz = MyAnnotation.class;
            Method amethod = aclazz.getMethod("value");
            String[] values= (String[])amethod.invoke(annotation);
            System.out.println(values[0]);
            Class fieldClass = field.getClass();

            Constructor con = fieldClass.getConstructor(String.class);
            amethod.invoke(aclazz,con.newInstance(values[0]));
            //4.执行注解里面的方法
//            String[] values= annotation.value();
//            System.out.println(values[0]);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
