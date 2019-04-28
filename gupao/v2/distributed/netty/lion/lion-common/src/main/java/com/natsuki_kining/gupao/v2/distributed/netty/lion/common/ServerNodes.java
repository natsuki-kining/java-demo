
package com.natsuki_kining.gupao.v2.distributed.netty.lion.common;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.srd.CommonServiceNode;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.srd.ServiceNames;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.srd.ServiceNode;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.config.CC;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.config.ConfigTools;

/**
 *
 *
 */
public class ServerNodes {

    public static ServiceNode cs() {
        CommonServiceNode node = new CommonServiceNode();
        node.setHost(ConfigTools.getConnectServerRegisterIp());
        node.setPort(CC.lion.net.connect_server_port);
        node.setPersistent(false);
        node.setServiceName(ServiceNames.CONN_SERVER);
        node.setAttrs(CC.lion.net.connect_server_register_attr);
        return node;
    }

    public static ServiceNode ws() {
        CommonServiceNode node = new CommonServiceNode();
        node.setHost(ConfigTools.getConnectServerRegisterIp());
        node.setPort(CC.lion.net.ws_server_port);
        node.setPersistent(false);
        node.setServiceName(ServiceNames.WS_SERVER);
        //node.addAttr(ATTR_PUBLIC_IP, ConfigTools.getPublicIp());
        return node;
    }

    public static ServiceNode gs() {
        CommonServiceNode node = new CommonServiceNode();
        node.setHost(ConfigTools.getGatewayServerRegisterIp());
        node.setPort(CC.lion.net.gateway_server_port);
        node.setPersistent(false);
        node.setServiceName(ServiceNames.GATEWAY_SERVER);
        return node;
    }
}
