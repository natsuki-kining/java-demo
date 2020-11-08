
package com.natsuki_kining.gupao.v2.distributed.netty.lion.boot.job;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.net.DnsMappingManager;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.core.LionServer;

/**
 */
public final class HttpProxyBoot extends BootJob {

    private final LionServer lionServer;

    public HttpProxyBoot(LionServer lionServer) {
        this.lionServer = lionServer;
    }

    @Override
    protected void start() {
        lionServer.getHttpClient().syncStart();
        DnsMappingManager.create().start();

        startNext();
    }

    @Override
    protected void stop() {
        stopNext();
        lionServer.getHttpClient().syncStop();
        DnsMappingManager.create().stop();
    }
}
