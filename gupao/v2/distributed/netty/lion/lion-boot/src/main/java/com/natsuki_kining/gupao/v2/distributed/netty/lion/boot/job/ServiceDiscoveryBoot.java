
package com.natsuki_kining.gupao.v2.distributed.netty.lion.boot.job;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.ServiceDiscoveryFactory;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.log.Logs;

/**
 */
public final class ServiceDiscoveryBoot extends BootJob {

    @Override
    protected void start() {
        Logs.Console.info("init service discovery waiting for connected...");
        ServiceDiscoveryFactory.create().syncStart();
        startNext();
    }

    @Override
    protected void stop() {
        stopNext();
        ServiceDiscoveryFactory.create().syncStop();
        Logs.Console.info("service discovery closed...");
    }
}
