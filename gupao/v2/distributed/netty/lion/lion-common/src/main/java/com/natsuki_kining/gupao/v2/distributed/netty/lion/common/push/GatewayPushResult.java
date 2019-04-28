
package com.natsuki_kining.gupao.v2.distributed.netty.lion.common.push;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.common.message.gateway.GatewayPushMessage;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.Jsons;

/**
 *
 *
 */
public final class GatewayPushResult {
    public String userId;
    public Integer clientType;
    public Object[] timePoints;

    public GatewayPushResult() {
    }

    public GatewayPushResult(String userId, Integer clientType, Object[] timePoints) {
        this.userId = userId;
        this.timePoints = timePoints;
        if (clientType > 0) this.clientType = clientType;
    }

    public static String toJson(GatewayPushMessage message, Object[] timePoints) {
        return Jsons.toJson(new GatewayPushResult(message.userId, message.clientType, timePoints));
    }

    public static GatewayPushResult fromJson(String json) {
        if (json == null) return null;
        return Jsons.fromJson(json, GatewayPushResult.class);
    }
}
