
package com.natsuki_kining.gupao.v2.distributed.netty.lion.registion.zk;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Spi;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.ServiceRegistryFactory;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.srd.ServiceRegistry;

/**
 */
@Spi(order = 1)
public final class ZKRegistryFactory implements ServiceRegistryFactory {
    @Override
    public ServiceRegistry get() {
        return ZKServiceRegistryAndDiscovery.I;
    }
}
