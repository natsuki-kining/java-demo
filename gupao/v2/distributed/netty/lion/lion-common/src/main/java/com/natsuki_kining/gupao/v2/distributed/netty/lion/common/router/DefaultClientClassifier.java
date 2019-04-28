
package com.natsuki_kining.gupao.v2.distributed.netty.lion.common.router;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.router.ClientClassifier;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Spi;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.router.ClientClassifierFactory;

/**
 *
 *
 */
@Spi(order = 1)
public final class DefaultClientClassifier implements ClientClassifier, ClientClassifierFactory {

    @Override
    public int getClientType(String osName) {
        return ClientType.find(osName).type;
    }

    @Override
    public ClientClassifier get() {
        return this;
    }
}
