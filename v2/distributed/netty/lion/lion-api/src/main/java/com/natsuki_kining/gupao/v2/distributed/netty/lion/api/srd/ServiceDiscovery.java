
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.srd;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.service.Service;

import java.util.List;

/**
 *
 */
public interface ServiceDiscovery extends Service {

    List<ServiceNode> lookup(String path);

    void subscribe(String path, ServiceListener listener);

    void unsubscribe(String path, ServiceListener listener);
}
