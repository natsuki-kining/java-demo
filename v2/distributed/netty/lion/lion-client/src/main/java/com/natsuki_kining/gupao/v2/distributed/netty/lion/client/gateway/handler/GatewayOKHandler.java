
package com.natsuki_kining.gupao.v2.distributed.netty.lion.client.gateway.handler;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.connection.Connection;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.protocol.Command;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.protocol.Packet;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.client.LionClient;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.client.push.PushRequest;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.client.push.PushRequestBus;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.common.handler.BaseMessageHandler;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.common.message.OkMessage;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.common.push.GatewayPushResult;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.log.Logs;

/**
 *
 */
public final class GatewayOKHandler extends BaseMessageHandler<OkMessage> {

    private PushRequestBus pushRequestBus;

    public GatewayOKHandler(LionClient lionClient) {
        this.pushRequestBus = lionClient.getPushRequestBus();
    }

    @Override
    public OkMessage decode(Packet packet, Connection connection) {
        return new OkMessage(packet, connection);
    }

    @Override
    public void handle(OkMessage message) {
        if (message.cmd == Command.GATEWAY_PUSH.cmd) {
            PushRequest request = pushRequestBus.getAndRemove(message.getSessionId());
            if (request == null) {
                Logs.PUSH.warn("receive a gateway response, but request has timeout. message={}", message);
                return;
            }
            request.onSuccess(GatewayPushResult.fromJson(message.data));//推送成功
        }
    }
}
