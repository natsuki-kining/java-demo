package com.natsuki_kining.gupao.v2.source_code.design_pattern.proxy.gpproxy;

import java.lang.reflect.Method;

public interface GpInvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
