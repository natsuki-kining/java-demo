

package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.event;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.connection.Connection;

public final class ConnectionCloseEvent implements Event {
    public final Connection connection;


    public ConnectionCloseEvent(Connection connection) {
        this.connection = connection;
    }
}
