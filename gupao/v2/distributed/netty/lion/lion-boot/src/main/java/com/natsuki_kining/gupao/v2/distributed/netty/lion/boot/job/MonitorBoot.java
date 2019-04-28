
package com.natsuki_kining.gupao.v2.distributed.netty.lion.boot.job;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.core.LionServer;

/**
 */
public final class MonitorBoot extends BootJob {

    private final LionServer lionServer;

    public MonitorBoot(LionServer lionServer) {
        this.lionServer = lionServer;
    }

    @Override
    protected void start() {
        lionServer.getMonitor().start();
        startNext();
    }

    @Override
    protected void stop() {
        stopNext();
        lionServer.getMonitor().stop();
    }
}
