
package com.natsuki_kining.gupao.v2.distributed.netty.lion.core.server;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.core.LionServer;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.core.handler.AdminHandler;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.network.netty.server.NettyTCPServer;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.config.CC;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.thread.ThreadNames;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public final class AdminServer extends NettyTCPServer {

    private AdminHandler adminHandler;

    private LionServer lionServer;

    public AdminServer(LionServer lionServer) {
        super(CC.lion.net.admin_server_port);
        this.lionServer = lionServer;
    }

    @Override
    public void init() {
        super.init();
        this.adminHandler = new AdminHandler(lionServer);
    }

    @Override
    protected void initPipeline(ChannelPipeline pipeline) {
        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        super.initPipeline(pipeline);
    }

    @Override
    public ChannelHandler getChannelHandler() {
        return adminHandler;
    }

    @Override
    protected ChannelHandler getDecoder() {
        return new StringDecoder();
    }

    @Override
    protected ChannelHandler getEncoder() {
        return new StringEncoder();
    }

    @Override
    protected int getWorkThreadNum() {
        return 1;
    }

    @Override
    protected String getBossThreadName() {
        return ThreadNames.T_ADMIN_BOSS;
    }

    @Override
    protected String getWorkThreadName() {
        return ThreadNames.T_ADMIN_WORKER;
    }
}
