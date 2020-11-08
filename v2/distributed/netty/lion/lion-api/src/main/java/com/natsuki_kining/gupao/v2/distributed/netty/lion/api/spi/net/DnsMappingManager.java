
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.net;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.service.Service;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.SpiLoader;

/**
 *
 */
public interface DnsMappingManager extends Service {

    static DnsMappingManager create() {
        return SpiLoader.load(DnsMappingManager.class);
    }

    DnsMapping lookup(String origin);
}
