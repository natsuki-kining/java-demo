
package com.natsuki_kining.gupao.v2.distributed.netty.lion.core.router;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.event.UserOfflineEvent;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.event.UserOnlineEvent;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.MQClient;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.MQClientFactory;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.common.router.RemoteRouterManager;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.common.user.UserManager;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.event.EventConsumer;

import static com.natsuki_kining.gupao.v2.distributed.netty.lion.api.event.Topics.OFFLINE_CHANNEL;
import static com.natsuki_kining.gupao.v2.distributed.netty.lion.api.event.Topics.ONLINE_CHANNEL;


public final class UserEventConsumer extends EventConsumer {

    private final MQClient mqClient = MQClientFactory.create();

    private final UserManager userManager;

    public UserEventConsumer(RemoteRouterManager remoteRouterManager) {
        this.userManager = new UserManager(remoteRouterManager);
    }

    @Subscribe
    @AllowConcurrentEvents
    void on(UserOnlineEvent event) {
        userManager.addToOnlineList(event.getUserId());
        mqClient.publish(ONLINE_CHANNEL, event.getUserId());
    }

    @Subscribe
    @AllowConcurrentEvents
    void on(UserOfflineEvent event) {
        userManager.remFormOnlineList(event.getUserId());
        mqClient.publish(OFFLINE_CHANNEL, event.getUserId());
    }

    public UserManager getUserManager() {
        return userManager;
    }
}
