
package com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.event;

public abstract class EventConsumer {

    public EventConsumer() {
        EventBus.register(this);
    }

}
