package com.natsuki_kining.gupao.v2.source_code.design_pattern.proxy.jdkproxy;

import com.natsuki_kining.gupao.v2.source_code.design_pattern.proxy.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKWorkProxy implements InvocationHandler {


    private Person targer;

    public Object getInstance(Person targer) throws Exception{
        this.targer = targer;

        Class<? extends Person> clazz = targer.getClass();

        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);

    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("获取简历");
        System.out.println("查找公司投递简历");
        method.invoke(this.targer,null);
        System.out.println("面试成功");
        System.out.println("入职");
        System.out.println("上班");
        return null;
    }
}
