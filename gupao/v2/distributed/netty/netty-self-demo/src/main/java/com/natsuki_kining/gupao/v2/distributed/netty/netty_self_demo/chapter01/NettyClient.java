package com.natsuki_kining.gupao.v2.distributed.netty.netty_self_demo.chapter01;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class NettyClient extends Thread {

    public NettyClient() {
    }

    public NettyClient(String name) {
        super(name);
    }

    private static String IP = "127.0.0.1";
    private static int PORT = 6666;

    @Override
    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();
                            pipeline.addLast("frameEncoder",new LengthFieldPrepender(4))
                                    .addLast("encoder",new StringEncoder(CharsetUtil.UTF_8))
                                    .addLast("decoder",new StringDecoder(CharsetUtil.UTF_8))
                                    .addLast("userHandler",new UserHandler());
                        }
                    });

            for (int i = 0,len = 10;i<len;i++){
                ChannelFuture future = bootstrap.connect(IP, PORT).sync();
                future.channel().writeAndFlush("hello netty server,I am " + Thread.currentThread().getName()+i);
                future.channel().closeFuture().sync();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        for (int i = 0,len = 3;i<len;i++){
            new NettyClient("user thread No."+i).start();
        }
    }
}
