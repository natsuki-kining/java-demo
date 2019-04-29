package com.natsuki_kining.network.websocket.netty.chat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebChatServerInitializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new HttpServerCodec()) //将请求和响应消息编码或者解码为HTTP协议的消息
                .addLast(new HttpObjectAggregator(64*1024)) //缓存区、最大值
                .addLast(new ChunkedWriteHandler()) //负责向客户端发送html的页面文件
                .addLast(new HttpRequestHandler("/chat"))
                .addLast(new WebSocketServerProtocolHandler("/chat"))
                .addLast(new TextWebSocketFrameHandler());


    }
}
