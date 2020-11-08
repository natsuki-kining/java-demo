
package com.natsuki_kining.gupao.v2.distributed.netty.lion.cacher.redis.manager;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Spi;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.CacheManager;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.CacheManagerFactory;

/**
 *
 *
 */
@Spi(order = 1)
public final class RedisCacheManagerFactory implements CacheManagerFactory {
    @Override
    public CacheManager get() {
        return RedisManager.I;
    }
}
