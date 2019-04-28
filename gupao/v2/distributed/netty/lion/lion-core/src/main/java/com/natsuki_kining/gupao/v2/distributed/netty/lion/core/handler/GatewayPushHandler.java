
package com.natsuki_kining.gupao.v2.distributed.netty.lion.core.handler;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.connection.Connection;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.protocol.Packet;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.common.handler.BaseMessageHandler;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.common.message.gateway.GatewayPushMessage;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.core.push.PushCenter;


public final class GatewayPushHandler extends BaseMessageHandler<GatewayPushMessage> {

    private final PushCenter pushCenter;

    public GatewayPushHandler(PushCenter pushCenter) {
        this.pushCenter = pushCenter;
    }

    @Override
    public GatewayPushMessage decode(Packet packet, Connection connection) {
        return new GatewayPushMessage(packet, connection);
    }

    @Override
    public void handle(GatewayPushMessage message) {
        pushCenter.push(message);
    }
}
