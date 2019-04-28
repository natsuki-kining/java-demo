

package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.event;


import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.connection.Connection;

public final class ConnectionConnectEvent implements Event {
    public final Connection connection;

    public ConnectionConnectEvent(Connection connection) {
        this.connection = connection;
    }
}
