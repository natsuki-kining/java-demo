
package com.natsuki_kining.gupao.v2.distributed.netty.lion.core.handler;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.connection.Connection;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.protocol.Packet;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.common.handler.BaseMessageHandler;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.common.message.AckMessage;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.core.LionServer;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.core.ack.AckTask;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.core.ack.AckTaskQueue;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.log.Logs;


public final class AckHandler extends BaseMessageHandler<AckMessage> {

    private final AckTaskQueue ackTaskQueue;

    public AckHandler(LionServer lionServer) {
        this.ackTaskQueue = lionServer.getPushCenter().getAckTaskQueue();
    }


    @Override
    public AckMessage decode(Packet packet, Connection connection) {
        return new AckMessage(packet, connection);
    }

    @Override
    public void handle(AckMessage message) {
        AckTask task = ackTaskQueue.getAndRemove(message.getSessionId());
        if (task == null) {//ack 超时了
            Logs.PUSH.info("receive client ack, but task timeout message={}", message);
            return;
        }

        task.onResponse();//成功收到客户的ACK响应
    }
}
