
package com.natsuki_kining.gupao.v2.distributed.netty.lion.client.gateway.connection;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.connection.Connection;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.service.BaseService;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.srd.ServiceListener;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.client.LionClient;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.common.message.BaseMessage;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.config.CC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 */
public abstract class GatewayConnectionFactory extends BaseService implements ServiceListener {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static GatewayConnectionFactory create(LionClient lionClient) {
        return CC.lion.net.udpGateway() ? new GatewayUDPConnectionFactory(lionClient) : new GatewayTCPConnectionFactory(lionClient);
    }

    abstract public Connection getConnection(String hostAndPort);

    abstract public <M extends BaseMessage> boolean send(String hostAndPort, Function<Connection, M> creator, Consumer<M> sender);

    abstract public <M extends BaseMessage> boolean broadcast(Function<Connection, M> creator, Consumer<M> sender);

}
