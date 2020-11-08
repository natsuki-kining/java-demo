
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Factory;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.SpiLoader;


public interface CacheManagerFactory extends Factory<CacheManager> {
    static CacheManager create() {
        return SpiLoader.load(CacheManagerFactory.class).get();
    }
}
