
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.router;


public interface Router<T> {

    T getRouteValue();

    RouterType getRouteType();

    enum RouterType {
        LOCAL, REMOTE
    }

}
