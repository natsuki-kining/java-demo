
package com.natsuki_kining.gupao.v2.distributed.rpc.client;

import com.natsuki_kining.gupao.v2.distributed.rpc.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 专门做socket管理
 *
 * @Author natsuki_kining
 * @Date 2019/11/26 16:43
 **/
public class TCPTransport {

    private String host;
    private int port;

    public TCPTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private Socket newSocket(){
        System.out.println("创建一个新的连接");
        Socket socket = null;
        try{
            socket = new Socket(host,port);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("建立连接失败。");
        }
        return socket;
    }

    public Object send(RpcRequest rpcRequest){
        Socket socket = null;
        try{
            socket = newSocket();

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object result = objectInputStream.readObject();

            objectOutputStream.close();
            objectInputStream.close();

            return result;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("发起远程请求操作异常。");
        }finally {
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
