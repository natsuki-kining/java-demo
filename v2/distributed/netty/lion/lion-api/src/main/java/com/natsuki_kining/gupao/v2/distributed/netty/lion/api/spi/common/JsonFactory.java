
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Factory;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.SpiLoader;


public interface JsonFactory extends Factory<Json> {

    static Json create() {
        return SpiLoader.load(JsonFactory.class).get();
    }
}
