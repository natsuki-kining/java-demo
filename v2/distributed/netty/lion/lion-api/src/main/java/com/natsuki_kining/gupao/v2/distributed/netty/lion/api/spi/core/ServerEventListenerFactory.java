
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.core;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.common.ServerEventListener;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Factory;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.SpiLoader;


public interface ServerEventListenerFactory extends Factory<ServerEventListener> {
    static ServerEventListener create() {
        return SpiLoader.load(ServerEventListenerFactory.class).get();
    }
}
