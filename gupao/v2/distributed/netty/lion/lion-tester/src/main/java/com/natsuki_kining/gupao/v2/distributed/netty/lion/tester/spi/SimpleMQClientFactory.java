
package com.natsuki_kining.gupao.v2.distributed.netty.lion.tester.spi;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Spi;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.MQClient;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.MQMessageReceiver;

/**
 */
@Spi(order = 2)
public final class SimpleMQClientFactory implements com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.MQClientFactory {
    private MQClient mqClient = new MQClient() {
        @Override
        public void subscribe(String topic, MQMessageReceiver receiver) {
            System.err.println("subscribe " + topic);
        }

        @Override
        public void publish(String topic, Object message) {
            System.err.println("publish " + topic + " " + message);
        }
    };

    @Override
    public MQClient get() {
        return mqClient;
    }
}
