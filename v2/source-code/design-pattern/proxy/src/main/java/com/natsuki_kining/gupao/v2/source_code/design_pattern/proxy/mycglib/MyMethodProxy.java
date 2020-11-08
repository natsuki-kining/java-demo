package com.natsuki_kining.gupao.v2.source_code.design_pattern.proxy.mycglib;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyMethodProxy{

    private Method method;

    public MyMethodProxy(Method method){
        this.method = method;
    }

    public void invokeSuper(Object o, Object[] objects) {
        try {
            method.invoke(o,objects);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
