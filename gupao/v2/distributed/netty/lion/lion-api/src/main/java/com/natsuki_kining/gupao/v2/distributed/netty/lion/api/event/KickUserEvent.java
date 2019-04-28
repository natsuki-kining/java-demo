
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.event;


public final class KickUserEvent implements Event {
    public final String userId;
    public final String deviceId;
    public final String fromServer;

    public KickUserEvent(String userId, String deviceId, String fromServer) {
        this.userId = userId;
        this.deviceId = deviceId;
        this.fromServer = fromServer;
    }
}
