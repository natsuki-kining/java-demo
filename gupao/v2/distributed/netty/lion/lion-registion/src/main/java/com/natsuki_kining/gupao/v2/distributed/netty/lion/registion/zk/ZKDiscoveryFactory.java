
package com.natsuki_kining.gupao.v2.distributed.netty.lion.registion.zk;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Spi;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.ServiceDiscoveryFactory;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.srd.ServiceDiscovery;

/**
 *
 */
@Spi(order = 1)
public final class ZKDiscoveryFactory implements ServiceDiscoveryFactory {
    @Override
    public ServiceDiscovery get() {
        return ZKServiceRegistryAndDiscovery.I;
    }
}
