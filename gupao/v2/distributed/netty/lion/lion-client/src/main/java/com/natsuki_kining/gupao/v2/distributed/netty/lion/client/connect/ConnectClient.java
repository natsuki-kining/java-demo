
package com.natsuki_kining.gupao.v2.distributed.netty.lion.client.connect;

import com.google.common.eventbus.Subscribe;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.event.ConnectionCloseEvent;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.network.netty.client.NettyTCPClient;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.event.EventBus;
import io.netty.channel.ChannelHandler;

public class ConnectClient extends NettyTCPClient {
    private final ConnClientChannelHandler handler;

    public ConnectClient(String host, int port, ClientConfig config) {
        handler = new ConnClientChannelHandler(config);
        EventBus.register(this);
    }

    @Override
    public ChannelHandler getChannelHandler() {
        return handler;
    }

    @Subscribe
    void on(ConnectionCloseEvent event) {
        this.stop();
    }

    @Override
    protected int getWorkThreadNum() {
        return 1;
    }
}
