package com.natsuki_kining.network.websocket.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        Channel channel = ctx.channel();//获取发送消息的人的连接通道
        for (Channel ch : channels){
            if (ch != channel){
                ch.writeAndFlush(new TextWebSocketFrame("[用户"+channel.remoteAddress()+"说]:"+msg.text()+"\n"));
            }else{
                ch.writeAndFlush(new TextWebSocketFrame("[我"+channel.remoteAddress()+"说]:"+msg.text()+"\n"));
            }

        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //当有客户端连接时，handlerAdded会自动调用，那么就该将连上的客户端记录下来
        Channel channel = ctx.channel();
        //进入聊天室

        for(Channel ch : channels){
            if (ch != channel){
                ch.writeAndFlush("[欢迎"+channel.remoteAddress()+"进入聊天室]");
            }
        }
        channels.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        for(Channel ch : channels){
            if (ch != channel){
                ch.writeAndFlush("["+channel.remoteAddress()+"离开聊天室]");
            }
        }
        channels.remove(channel);
    }
}
