
package com.natsuki_kining.gupao.v2.distributed.netty.lion.tester.spi;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Spi;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.CacheManager;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.CacheManagerFactory;

/**
 */
@Spi(order = 2)
public final class SimpleCacheMangerFactory implements CacheManagerFactory {
    @Override
    public CacheManager get() {
        return FileCacheManger.I;
    }
}
