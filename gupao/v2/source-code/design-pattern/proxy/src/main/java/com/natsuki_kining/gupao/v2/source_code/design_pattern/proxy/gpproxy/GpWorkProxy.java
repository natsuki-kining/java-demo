package com.natsuki_kining.gupao.v2.source_code.design_pattern.proxy.gpproxy;

import com.natsuki_kining.gupao.v2.source_code.design_pattern.proxy.Person;

import java.lang.reflect.Method;

public class GpWorkProxy implements GpInvocationHandler{
public class GpWorkProxy implements GpInvocationHandler{

    private Person target;

    public Object getInstance(Person target){
        this.target = target;
        Object object = null;
        try {
            object = GpProxy.newProxyInstance(new GpClassLoader(), target.getClass().getInterfaces(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("获取简历");
        System.out.println("查找公司投递简历");
        method.invoke(this.target,null);
        System.out.println("面试成功");
        System.out.println("入职");
        System.out.println("上班");
        return null;
    }
}
