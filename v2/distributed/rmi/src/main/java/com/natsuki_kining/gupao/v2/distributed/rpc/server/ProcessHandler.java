
package com.natsuki_kining.gupao.v2.distributed.rpc.server;

import com.natsuki_kining.gupao.v2.distributed.rpc.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * 处理socket 请求
 *
 * @Author natsuki_kining
 * @Date 2019/11/26 16:28
 **/
public class ProcessHandler implements Runnable {

    private Socket socket;
    private Object service;//服务端发布的服务

    public ProcessHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        //处理socket 请求
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object object = objectInputStream.readObject();
            RpcRequest rpcRequest = (RpcRequest) object;
            Object invoke = invoke(rpcRequest);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(invoke);
            objectOutputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (objectInputStream != null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectOutputStream != null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 反射调用
     * @param rpcRequest
     * @return
     */
    private Object invoke(RpcRequest rpcRequest) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Object[] parameters = rpcRequest.getParameters();
        Class<?>[] types = new Class<?>[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            types[i] = parameters[i].getClass();
        }
        Method method = service.getClass().getMethod(rpcRequest.getMethodName(), types);
        Object invoke = method.invoke(service, parameters);
        return invoke;
    }
}
