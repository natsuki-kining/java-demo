
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.srd;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.service.Service;

/**
 *
 *
 */
public interface ServiceRegistry extends Service {

    void register(ServiceNode node);

    void deregister(ServiceNode node);
}
