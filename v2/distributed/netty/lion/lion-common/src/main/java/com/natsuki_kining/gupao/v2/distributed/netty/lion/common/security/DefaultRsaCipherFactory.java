
package com.natsuki_kining.gupao.v2.distributed.netty.lion.common.security;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.connection.Cipher;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Spi;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.core.RsaCipherFactory;

/**
 */
@Spi
public class DefaultRsaCipherFactory implements RsaCipherFactory {
    private static final RsaCipher RSA_CIPHER = RsaCipher.create();

    @Override
    public Cipher get() {
        return RSA_CIPHER;
    }
}
