
package com.natsuki_kining.gupao.v2.distributed.netty.lion.cacher.redis.mq;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.LionContext;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.MQClient;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.MQMessageReceiver;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.cacher.redis.manager.RedisManager;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.monitor.service.MonitorService;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.log.Logs;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public final class ListenerDispatcher implements MQClient {

    private final Map<String, List<MQMessageReceiver>> subscribes = Maps.newTreeMap();

    private final Subscriber subscriber;

    private Executor executor;

    @Override
    public void init(LionContext context) {
        executor = ((MonitorService) context.getMonitor()).getThreadPoolManager().getRedisExecutor();
    }

    public ListenerDispatcher() {
        this.subscriber = new Subscriber(this);
    }

    public void onMessage(String channel, String message) {
        List<MQMessageReceiver> listeners = subscribes.get(channel);
        if (listeners == null) {
            Logs.CACHE.info("cannot find listener:{}, {}", channel, message);
            return;
        }

        for (MQMessageReceiver listener : listeners) {
            executor.execute(() -> listener.receive(channel, message));
        }
    }

    public void subscribe(String channel, MQMessageReceiver listener) {
        subscribes.computeIfAbsent(channel, k -> Lists.newArrayList()).add(listener);
        RedisManager.I.subscribe(subscriber, channel);
    }

    @Override
    public void publish(String topic, Object message) {
        RedisManager.I.publish(topic, message);
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }
}
