
package com.natsuki_kining.gupao.v2.distributed.rpc;

import java.io.Serializable;

/**
 * 远程传输对象
 * 告诉服务端当前调用的是哪个服务，哪个方法
 *
 * @Author natsuki_kining
 * @Date 2019/11/26 16:33
 **/
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = -1523050555384738021L;

    private String className;
    private String methodName;
    private Object[] parameters;

    public RpcRequest(String className, String methodName, Object[] parameters) {
        this.className = className;
        this.methodName = methodName;
        this.parameters = parameters;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
