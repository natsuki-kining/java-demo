
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.core;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.connection.Cipher;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Factory;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.SpiLoader;


public interface RsaCipherFactory extends Factory<Cipher> {
    static Cipher create() {
        return SpiLoader.load(RsaCipherFactory.class).get();
    }
}
