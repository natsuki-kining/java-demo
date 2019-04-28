
package com.natsuki_kining.gupao.v2.distributed.netty.lion.common.memory;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.protocol.Command;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.protocol.Packet;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.protocol.UDPPacket;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.config.CC;

/**
 */
public interface PacketFactory {
    PacketFactory FACTORY = CC.lion.net.udpGateway() ? UDPPacket::new : Packet::new;

    static Packet get(Command command) {
        return FACTORY.create(command);
    }

    Packet create(Command command);
}