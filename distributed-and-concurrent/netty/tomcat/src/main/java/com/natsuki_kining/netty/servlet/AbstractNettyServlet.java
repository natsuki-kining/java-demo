package com.natsuki_kining.netty.servlet;

import com.natsuki_kining.netty.http.NettyRequest;
import com.natsuki_kining.netty.http.NettyResponse;

/**
 * TODO
 *
 * @Author natsuki_kining
 * @Date 2021/4/16 23:37
 **/
public abstract class AbstractNettyServlet {

    public void service(NettyRequest request,NettyResponse response) throws Exception{
        if ("GET".equalsIgnoreCase(request.getMethod())){
            doGet(request,response);
        }else{
            doPost(request,response);
        }
    }

    public abstract void doGet(NettyRequest request, NettyResponse response) throws Exception;

    public abstract void doPost(NettyRequest request, NettyResponse response) throws Exception;

}
