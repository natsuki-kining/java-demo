
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.event;


import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.router.Router;

public final class RouterChangeEvent implements Event {
    public final String userId;
    public final Router<?> router;

    public RouterChangeEvent(String userId, Router<?> router) {
        this.userId = userId;
        this.router = router;
    }
}
