package com.natsuki_kining.gupao.v2.source_code.design_pattern.proxy.mycglib;

import java.lang.reflect.Method;

public interface MyMethodInterceptor {

    Object intercept(Object o, Method method, Object[] objects, MyMethodProxy myMethodProxy) throws Throwable;
}
