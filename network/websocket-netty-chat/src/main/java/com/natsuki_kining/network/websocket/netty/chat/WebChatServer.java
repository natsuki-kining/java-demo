package com.natsuki_kining.network.websocket.netty.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class WebChatServer {

    private int port;

    public WebChatServer(int port){
        this.port = port;
    }

    public void start(){
        //定义两个线程组
        EventLoopGroup boss = new NioEventLoopGroup(); //负责处理新客户端的接入
        EventLoopGroup work = new NioEventLoopGroup(); //负责处理客户端的网络请求

        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss,work)
                    .channel(NioServerSocketChannel.class)  //使用NIO来通讯
                    .childHandler(new WebChatServerInitializer())
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true);

            ChannelFuture future = bootstrap.bind(port).sync();
            System.out.println("服务端已启动");
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        new WebChatServer(8080).start();
    }
}
