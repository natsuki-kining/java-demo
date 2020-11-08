
package com.natsuki_kining.gupao.v2.distributed.netty.lion.tester.spi;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Spi;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.ServiceDiscoveryFactory;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.srd.ServiceDiscovery;

/**
 */
@Spi(order = 2)
public final class SimpleDiscoveryFactory implements ServiceDiscoveryFactory {
    @Override
    public ServiceDiscovery get() {
        return FileSrd.I;
    }
}
