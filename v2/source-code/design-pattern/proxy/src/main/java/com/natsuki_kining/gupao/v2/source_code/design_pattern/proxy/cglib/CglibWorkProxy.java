package com.natsuki_kining.gupao.v2.source_code.design_pattern.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibWorkProxy implements MethodInterceptor {

    public Object getInstance(Class<?> clazz){

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);

        return enhancer.create();
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("获取简历");
        System.out.println("查找公司投递简历");
        methodProxy.invokeSuper(o,objects);
        System.out.println("面试成功");
        System.out.println("入职");
        System.out.println("上班");
        return null;
    }
}
