package com.natsuki_kining.gupao.v2.distributed.netty.netty_self_demo.chapter01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * netty 服务端
 */
public class NettyServer {

    private static String IP = "127.0.0.1";
    private static int PORT = 6666;

    private static int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2;
    private static int BIZTHREADSIZE = 100;

    private static EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
    private static EventLoopGroup workGroup = new NioEventLoopGroup(BIZTHREADSIZE);

    private static void start() throws Exception {
        ServerBootstrap bootstrap = initServerBootstarp();
        ChannelFuture future = bootstrap.bind(IP, PORT).sync();
        future.channel().closeFuture().sync();
        System.out.println("===================netty server started!");

    }

    private static ServerBootstrap initServerBootstarp() {

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4))
                                .addLast(new StringDecoder(CharsetUtil.UTF_8))
                                .addLast(new StringEncoder(CharsetUtil.UTF_8))
                                .addLast(new TcpServerHandler());
                    }
                });
        return serverBootstrap;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("===================netty server start......");
        start();
    }
}
