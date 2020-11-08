
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.router;


import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.router.ClientClassifierFactory;

public interface ClientClassifier {
    ClientClassifier I = ClientClassifierFactory.create();

    int getClientType(String osName);
}
