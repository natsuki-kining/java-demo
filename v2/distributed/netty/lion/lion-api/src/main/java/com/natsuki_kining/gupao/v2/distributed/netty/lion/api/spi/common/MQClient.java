
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Plugin;


public interface MQClient extends Plugin {

    void subscribe(String topic, MQMessageReceiver receiver);

    void publish(String topic, Object message);
}
