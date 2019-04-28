
package com.natsuki_kining.gupao.v2.distributed.netty.lion.boot.job;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.service.Listener;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.service.Server;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.ServiceRegistryFactory;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.srd.ServiceNode;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.log.Logs;

/**
 */
public final class ServerBoot extends BootJob {
    private final Server server;
    private final ServiceNode node;

    public ServerBoot(Server server, ServiceNode node) {
        this.server = server;
        this.node = node;
    }

    @Override
    public void start() {
        server.init();
        server.start(new Listener() {
            @Override
            public void onSuccess(Object... args) {
                Logs.Console.info("start {} success on:{}", server.getClass().getSimpleName(), args[0]);
                if (node != null) {//注册应用到zk
                    ServiceRegistryFactory.create().register(node);
                    Logs.RSD.info("register {} to srd success.", node);
                }
                startNext();
            }

            @Override
            public void onFailure(Throwable cause) {
                Logs.Console.error("start {} failure, jvm exit with code -1", server.getClass().getSimpleName(), cause);
                System.exit(-1);
            }
        });
    }

    @Override
    protected void stop() {
        stopNext();
        if (node != null) {
            ServiceRegistryFactory.create().deregister(node);
        }
        Logs.Console.info("try shutdown {}...", server.getClass().getSimpleName());
        server.stop().join();
        Logs.Console.info("{} shutdown success.", server.getClass().getSimpleName());
    }

    @Override
    protected String getName() {
        return super.getName() + '(' + server.getClass().getSimpleName() + ')';
    }
}
