
package com.natsuki_kining.gupao.v2.distributed.netty.lion.core.server;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.connection.ConnectionManager;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.protocol.Command;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.service.Listener;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.handler.PushHandlerFactory;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.common.MessageDispatcher;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.core.LionServer;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.core.handler.AckHandler;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.core.handler.BindUserHandler;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.core.handler.HandshakeHandler;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.network.netty.server.NettyTCPServer;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.config.CC;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;


public final class WebsocketServer extends NettyTCPServer {

    private final ChannelHandler channelHandler;

    private final MessageDispatcher messageDispatcher;

    private final ConnectionManager connectionManager;

    private final LionServer lionServer;

    public WebsocketServer(LionServer lionServer) {
        super(CC.lion.net.ws_server_port);
        this.lionServer = lionServer;
        this.messageDispatcher = new MessageDispatcher();
        this.connectionManager = new ServerConnectionManager(false);
        this.channelHandler = new WebSocketChannelHandler(connectionManager, messageDispatcher);
    }

    @Override
    public void init() {
        super.init();
        connectionManager.init();
        messageDispatcher.register(Command.HANDSHAKE, () -> new HandshakeHandler(lionServer));
        messageDispatcher.register(Command.BIND, () -> new BindUserHandler(lionServer));
        messageDispatcher.register(Command.UNBIND, () -> new BindUserHandler(lionServer));
        messageDispatcher.register(Command.PUSH, PushHandlerFactory::create);
        messageDispatcher.register(Command.ACK, () -> new AckHandler(lionServer));
    }

    @Override
    public void stop(Listener listener) {
        super.stop(listener);
        connectionManager.destroy();
    }

    @Override
    public EventLoopGroup getBossGroup() {
        return lionServer.getConnectionServer().getBossGroup();
    }

    @Override
    public EventLoopGroup getWorkerGroup() {
        return lionServer.getConnectionServer().getWorkerGroup();
    }

    @Override
    protected void initPipeline(ChannelPipeline pipeline) {
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(65536));
        pipeline.addLast(new WebSocketServerCompressionHandler());
        pipeline.addLast(new WebSocketServerProtocolHandler(CC.lion.net.ws_path, null, true));
        pipeline.addLast(new WebSocketIndexPageHandler());
        pipeline.addLast(getChannelHandler());
    }

    @Override
    protected void initOptions(ServerBootstrap b) {
        super.initOptions(b);
        b.option(ChannelOption.SO_BACKLOG, 1024);
        b.childOption(ChannelOption.SO_SNDBUF, 32 * 1024);
        b.childOption(ChannelOption.SO_RCVBUF, 32 * 1024);
    }

    @Override
    public ChannelHandler getChannelHandler() {
        return channelHandler;
    }

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public MessageDispatcher getMessageDispatcher() {
        return messageDispatcher;
    }
}
