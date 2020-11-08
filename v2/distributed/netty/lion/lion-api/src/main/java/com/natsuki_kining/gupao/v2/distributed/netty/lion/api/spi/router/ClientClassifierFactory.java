
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.router;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.router.ClientClassifier;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Factory;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.SpiLoader;

/**
 *
 *
 */
public interface ClientClassifierFactory extends Factory<ClientClassifier> {

    static ClientClassifier create() {
        return SpiLoader.load(ClientClassifierFactory.class).get();
    }
}
