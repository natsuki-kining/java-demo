
package com.natsuki_kining.gupao.v2.distributed.netty.lion.core.server;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.common.ServerEventListener;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Spi;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.core.ServerEventListenerFactory;


@Spi(order = 1)
public final class DefaultServerEventListener implements ServerEventListener, ServerEventListenerFactory {

    @Override
    public ServerEventListener get() {
        return this;
    }
}
