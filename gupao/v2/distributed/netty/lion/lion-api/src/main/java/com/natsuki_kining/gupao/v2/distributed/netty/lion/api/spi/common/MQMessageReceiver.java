
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common;


public interface MQMessageReceiver {
    void receive(String topic, Object message);
}
