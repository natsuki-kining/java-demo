

package com.natsuki_kining.gupao.v2.distributed.netty.lion.core.handler;


import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.connection.Connection;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.message.MessageHandler;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.protocol.Packet;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Spi;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.handler.PushHandlerFactory;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.common.handler.BaseMessageHandler;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.common.message.AckMessage;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.common.message.PushMessage;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.log.Logs;


@Spi(order = 1)
public final class ClientPushHandler extends BaseMessageHandler<PushMessage> implements PushHandlerFactory {

    @Override
    public PushMessage decode(Packet packet, Connection connection) {
        return new PushMessage(packet, connection);
    }

    @Override
    public void handle(PushMessage message) {
        Logs.PUSH.info("receive client push message={}", message);

        if (message.autoAck()) {
            AckMessage.from(message).sendRaw();
            Logs.PUSH.info("send ack for push message={}", message);
        }
        //biz code write here
    }

    @Override
    public MessageHandler get() {
        return this;
    }
}
