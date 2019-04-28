
package com.natsuki_kining.gupao.v2.distributed.netty.lion.core.session;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.connection.SessionContext;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.CacheManager;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.CacheManagerFactory;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.common.CacheKeys;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.common.Strings;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.config.CC;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.crypto.MD5Utils;


public final class ReusableSessionManager {
    private final int expiredTime = CC.lion.core.session_expired_time;
    private final CacheManager cacheManager = CacheManagerFactory.create();

    public boolean cacheSession(ReusableSession session) {
        String key = CacheKeys.getSessionKey(session.sessionId);
        cacheManager.set(key, ReusableSession.encode(session.context), expiredTime);
        return true;
    }

    public ReusableSession querySession(String sessionId) {
        String key = CacheKeys.getSessionKey(sessionId);
        String value = cacheManager.get(key, String.class);
        if (Strings.isBlank(value)) return null;
        return ReusableSession.decode(value);
    }

    public ReusableSession genSession(SessionContext context) {
        long now = System.currentTimeMillis();
        ReusableSession session = new ReusableSession();
        session.context = context;
        session.sessionId = MD5Utils.encrypt(context.deviceId + now);
        session.expireTime = now + expiredTime * 1000;
        return session;
    }
}
