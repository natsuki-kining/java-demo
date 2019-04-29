package com.natsuki_kining.network.websocket.netty.chat;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedNioFile;
import io.netty.util.concurrent.EventExecutorGroup;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URL;

public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final String chatUri;
    private static File indexFile;

    static {
        try{
            String path = HttpRequestHandler.class.getClassLoader().getResource("index.html").getPath();
            indexFile = new File(path);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public HttpRequestHandler(String chatUri) {
        this.chatUri = chatUri;
    }

    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        if(chatUri.equalsIgnoreCase(request.getUri())){
            //如果是websocket请求、则转到下一道工序处理
            ctx.fireChannelRead(request.retain());
        }else{
            //如果是http请求，则需要读取index.html页面发送给客户端浏览器;
            if (HttpHeaders.is100ContinueExpected(request)){
                FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.CONTINUE);
                ctx.writeAndFlush(response);
            }
            //读取默认的index.html页面
            RandomAccessFile file = new RandomAccessFile(indexFile,"r");
            //设置Http协议响应头
            HttpResponse response = new DefaultHttpResponse(request.getProtocolVersion(),HttpResponseStatus.OK);
            response.headers().set(HttpHeaders.Names.CONTENT_TYPE,"text/html;charset=utf-8");

            boolean keepAlive = HttpHeaders.isKeepAlive(request);
            if (keepAlive){
                response.headers().set(HttpHeaders.Names.CONTENT_LENGTH,file.length());
                response.headers().set(HttpHeaders.Names.CONNECTION,HttpHeaders.Values.KEEP_ALIVE);
            }

            ctx.write(response);
            //将html文件写到客户端
            ctx.write(new ChunkedNioFile(file.getChannel()));
            ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);

            if (!keepAlive){
                future.addListener(ChannelFutureListener.CLOSE);
            }
            file.close();

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
