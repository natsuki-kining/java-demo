
package com.natsuki_kining.gupao.v2.distributed.netty.lion.tester.spi;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Spi;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.ServiceRegistryFactory;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.srd.ServiceRegistry;

/**
 */
@Spi(order = 2)
public final class SimpleRegistryFactory implements ServiceRegistryFactory {
    @Override
    public ServiceRegistry get() {
        return FileSrd.I;
    }
}
