
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api;


import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.common.Monitor;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.CacheManager;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.MQClient;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.srd.ServiceDiscovery;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.srd.ServiceRegistry;

public interface LionContext {

    Monitor getMonitor();

    ServiceDiscovery getDiscovery();

    ServiceRegistry getRegistry();

    CacheManager getCacheManager();

    MQClient getMQClient();

}
