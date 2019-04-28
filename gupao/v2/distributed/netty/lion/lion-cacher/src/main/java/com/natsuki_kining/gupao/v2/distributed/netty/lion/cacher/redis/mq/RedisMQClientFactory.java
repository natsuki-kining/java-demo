
package com.natsuki_kining.gupao.v2.distributed.netty.lion.cacher.redis.mq;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Spi;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.MQClient;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.MQClientFactory;

/**
 *
 */
@Spi(order = 1)
public final class RedisMQClientFactory implements MQClientFactory {
    private ListenerDispatcher listenerDispatcher = new ListenerDispatcher();

    @Override
    public MQClient get() {
        return listenerDispatcher;
    }
}
