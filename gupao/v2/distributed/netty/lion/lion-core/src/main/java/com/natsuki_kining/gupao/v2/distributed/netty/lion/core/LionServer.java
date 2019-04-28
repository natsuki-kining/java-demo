
package com.natsuki_kining.gupao.v2.distributed.netty.lion.core;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.LionContext;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.*;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.srd.ServiceDiscovery;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.srd.ServiceNode;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.srd.ServiceRegistry;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.common.ServerNodes;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.core.push.PushCenter;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.core.router.RouterCenter;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.core.server.*;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.core.session.ReusableSessionManager;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.monitor.service.MonitorService;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.network.netty.http.HttpClient;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.network.netty.http.NettyHttpClient;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.event.EventBus;

import static com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.config.CC.lion.net.tcpGateway;

public final class LionServer implements LionContext {

    private ServiceNode connServerNode;
    private ServiceNode gatewayServerNode;
    private ServiceNode websocketServerNode;

    private ConnectionServer connectionServer;
    private WebsocketServer websocketServer;
    private GatewayServer gatewayServer;
    private AdminServer adminServer;
    private GatewayUDPConnector udpGatewayServer;

    private HttpClient httpClient;

    private PushCenter pushCenter;

    private ReusableSessionManager reusableSessionManager;

    private RouterCenter routerCenter;

    private MonitorService monitorService;


    public LionServer() {
        connServerNode = ServerNodes.cs();
        gatewayServerNode = ServerNodes.gs();
        websocketServerNode = ServerNodes.ws();

        monitorService = new MonitorService();
        EventBus.create(monitorService.getThreadPoolManager().getEventBusExecutor());

        reusableSessionManager = new ReusableSessionManager();

        pushCenter = new PushCenter(this);

        routerCenter = new RouterCenter(this);

        connectionServer = new ConnectionServer(this);

        websocketServer = new WebsocketServer(this);

        adminServer = new AdminServer(this);

        if (tcpGateway()) {
            gatewayServer = new GatewayServer(this);
        } else {
            udpGatewayServer = new GatewayUDPConnector(this);
        }
    }

    public boolean isTargetMachine(String host, int port) {
        return port == gatewayServerNode.getPort() && gatewayServerNode.getHost().equals(host);
    }

    public ServiceNode getConnServerNode() {
        return connServerNode;
    }

    public ServiceNode getGatewayServerNode() {
        return gatewayServerNode;
    }

    public ServiceNode getWebsocketServerNode() {
        return websocketServerNode;
    }

    public ConnectionServer getConnectionServer() {
        return connectionServer;
    }

    public GatewayServer getGatewayServer() {
        return gatewayServer;
    }

    public AdminServer getAdminServer() {
        return adminServer;
    }

    public GatewayUDPConnector getUdpGatewayServer() {
        return udpGatewayServer;
    }

    public WebsocketServer getWebsocketServer() {
        return websocketServer;
    }

    public HttpClient getHttpClient() {
        if (httpClient == null) {
            synchronized (this) {
                if (httpClient == null) {
                    httpClient = new NettyHttpClient();
                }
            }
        }
        return httpClient;
    }

    public PushCenter getPushCenter() {
        return pushCenter;
    }

    public ReusableSessionManager getReusableSessionManager() {
        return reusableSessionManager;
    }

    public RouterCenter getRouterCenter() {
        return routerCenter;
    }

    @Override
    public MonitorService getMonitor() {
        return monitorService;
    }

    @Override
    public ServiceDiscovery getDiscovery() {
        return ServiceDiscoveryFactory.create();
    }

    @Override
    public ServiceRegistry getRegistry() {
        return ServiceRegistryFactory.create();
    }

    @Override
    public CacheManager getCacheManager() {
        return CacheManagerFactory.create();
    }

    @Override
    public MQClient getMQClient() {
        return MQClientFactory.create();
    }
}
