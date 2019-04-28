
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Factory;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.SpiLoader;


public interface MQClientFactory extends Factory<MQClient> {

    static MQClient create() {
        return SpiLoader.load(MQClientFactory.class).get();
    }
}
