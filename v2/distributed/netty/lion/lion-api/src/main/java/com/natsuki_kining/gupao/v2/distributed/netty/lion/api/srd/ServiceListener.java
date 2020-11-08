
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.srd;

/**
 *
 *
 */
public interface ServiceListener {

    void onServiceAdded(String path, ServiceNode node);

    void onServiceUpdated(String path, ServiceNode node);

    void onServiceRemoved(String path, ServiceNode node);

}
