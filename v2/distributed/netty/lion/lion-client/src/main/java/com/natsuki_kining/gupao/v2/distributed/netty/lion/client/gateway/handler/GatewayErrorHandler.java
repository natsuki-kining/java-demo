
package com.natsuki_kining.gupao.v2.distributed.netty.lion.client.gateway.handler;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.connection.Connection;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.protocol.Command;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.protocol.Packet;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.client.LionClient;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.client.push.PushRequest;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.client.push.PushRequestBus;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.common.handler.BaseMessageHandler;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.common.message.ErrorMessage;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.log.Logs;

import static com.natsuki_kining.gupao.v2.distributed.netty.lion.common.ErrorCode.OFFLINE;
import static com.natsuki_kining.gupao.v2.distributed.netty.lion.common.ErrorCode.PUSH_CLIENT_FAILURE;
import static com.natsuki_kining.gupao.v2.distributed.netty.lion.common.ErrorCode.ROUTER_CHANGE;

/**
 *
 */
public final class GatewayErrorHandler extends BaseMessageHandler<ErrorMessage> {

    private final PushRequestBus pushRequestBus;

    public GatewayErrorHandler(LionClient lionClient) {
        this.pushRequestBus = lionClient.getPushRequestBus();
    }

    @Override
    public ErrorMessage decode(Packet packet, Connection connection) {
        return new ErrorMessage(packet, connection);
    }

    @Override
    public void handle(ErrorMessage message) {
        if (message.cmd == Command.GATEWAY_PUSH.cmd) {
            PushRequest request = pushRequestBus.getAndRemove(message.getSessionId());
            if (request == null) {
                Logs.PUSH.warn("receive a gateway response, but request has timeout. message={}", message);
                return;
            }

            Logs.PUSH.warn("receive an error gateway response, message={}", message);
            if (message.code == OFFLINE.errorCode) {//用户离线
                request.onOffline();
            } else if (message.code == PUSH_CLIENT_FAILURE.errorCode) {//下发到客户端失败
                request.onFailure();
            } else if (message.code == ROUTER_CHANGE.errorCode) {//用户路由信息更改
                request.onRedirect();
            }
        }
    }
}
