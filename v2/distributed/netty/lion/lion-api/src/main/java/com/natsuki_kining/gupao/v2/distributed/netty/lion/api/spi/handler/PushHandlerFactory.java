
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.handler;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.message.MessageHandler;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Factory;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.SpiLoader;

/**
 *
 */
public interface PushHandlerFactory extends Factory<MessageHandler> {
    static MessageHandler create() {
        return SpiLoader.load(PushHandlerFactory.class).get();
    }
}
