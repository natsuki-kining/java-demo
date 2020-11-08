
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.LionContext;

/**
 *
 *
 *
 */
public interface Plugin {

    default void init(LionContext context) {

    }

    default void destroy() {

    }
}
