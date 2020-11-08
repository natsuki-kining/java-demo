
package com.natsuki_kining.gupao.v2.distributed.netty.lion.common.handler;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.connection.Connection;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.protocol.Packet;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.common.message.OkMessage;

/**
 */
public class OkMessageHandler extends BaseMessageHandler<OkMessage> {
    @Override
    public OkMessage decode(Packet packet, Connection connection) {
        return new OkMessage(packet, connection);
    }

    @Override
    public void handle(OkMessage message) {

    }
}
