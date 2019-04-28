
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.message;


import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.connection.Connection;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.protocol.Packet;

public interface MessageHandler {
    void handle(Packet packet, Connection connection);
}
