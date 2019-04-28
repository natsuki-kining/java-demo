
package com.natsuki_kining.gupao.v2.distributed.netty.lion.client.push;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.push.PushSender;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Spi;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.client.PusherFactory;

/**
 */
@Spi
public class PushClientFactory implements PusherFactory {
    private volatile PushClient client;

    @Override
    public PushSender get() {
        if (client == null) {
            synchronized (PushClientFactory.class) {
                if (client == null) {
                    client = new PushClient();
                }
            }
        }
        return client;
    }
}
