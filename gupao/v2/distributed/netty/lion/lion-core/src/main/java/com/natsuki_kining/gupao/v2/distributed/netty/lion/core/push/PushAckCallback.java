package com.natsuki_kining.gupao.v2.distributed.netty.lion.core.push;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.push.IPushMessage;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.core.ack.AckCallback;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.core.ack.AckTask;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.common.TimeLine;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.log.Logs;

public final class PushAckCallback implements AckCallback {
    private final IPushMessage message;
    private final TimeLine timeLine;
    private final PushCenter pushCenter;

    public PushAckCallback(IPushMessage message, TimeLine timeLine, PushCenter pushCenter) {
        this.message = message;
        this.timeLine = timeLine;
        this.pushCenter = pushCenter;
    }

    @Override
    public void onSuccess(AckTask task) {
        pushCenter.getPushListener().onAckSuccess(message, timeLine.successEnd().getTimePoints());
        Logs.PUSH.info("[SingleUserPush] client ack success, timeLine={}, task={}", timeLine, task);
    }

    @Override
    public void onTimeout(AckTask task) {
        pushCenter.getPushListener().onTimeout(message, timeLine.timeoutEnd().getTimePoints());
        Logs.PUSH.warn("[SingleUserPush] client ack timeout, timeLine={}, task={}", timeLine, task);
    }
}