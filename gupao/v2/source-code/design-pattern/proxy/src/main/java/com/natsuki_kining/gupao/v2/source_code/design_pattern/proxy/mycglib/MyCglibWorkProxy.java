package com.natsuki_kining.gupao.v2.source_code.design_pattern.proxy.mycglib;

import java.lang.reflect.Method;

public class MyCglibWorkProxy implements MyMethodInterceptor {

    public Object getInstance(Class<?> clazz){

        MyEnhancer enhancer = new MyEnhancer();

        enhancer.setSuperClass(clazz);
        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MyMethodProxy myMethodProxy) throws Throwable {
        System.out.println("获取简历");
        System.out.println("查找公司投递简历");
        System.out.println(o.getClass());
        myMethodProxy.invokeSuper(o,objects);
        System.out.println("面试成功");
        System.out.println("入职");
        System.out.println("上班");
        return null;
    }
}
