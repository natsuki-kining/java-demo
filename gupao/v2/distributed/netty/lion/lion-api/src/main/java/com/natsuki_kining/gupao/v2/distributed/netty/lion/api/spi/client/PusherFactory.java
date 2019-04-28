
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.client;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.push.PushSender;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Factory;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.SpiLoader;


public interface PusherFactory extends Factory<PushSender> {
    static PushSender create() {
        return SpiLoader.load(PusherFactory.class).get();
    }
}
