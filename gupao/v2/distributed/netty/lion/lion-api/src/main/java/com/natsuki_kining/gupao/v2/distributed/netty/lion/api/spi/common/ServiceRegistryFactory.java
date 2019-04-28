
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Factory;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.SpiLoader;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.srd.ServiceRegistry;


public interface ServiceRegistryFactory extends Factory<ServiceRegistry> {
    static ServiceRegistry create() {
        return SpiLoader.load(ServiceRegistryFactory.class).get();
    }
}
