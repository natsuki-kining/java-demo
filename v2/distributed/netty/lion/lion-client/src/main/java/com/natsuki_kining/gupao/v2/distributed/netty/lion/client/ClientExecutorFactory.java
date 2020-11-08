
package com.natsuki_kining.gupao.v2.distributed.netty.lion.client;


import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Spi;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.common.CommonExecutorFactory;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.log.Logs;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.thread.NamedPoolThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import static com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.config.CC.lion.thread.pool.ack_timer;
import static com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.config.CC.lion.thread.pool.push_client;
import static com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.thread.ThreadNames.T_ARK_REQ_TIMER;
import static com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.thread.ThreadNames.T_PUSH_CLIENT_TIMER;

/**
 * 此线程池可伸缩，线程空闲一定时间后回收，新请求重新创建线程
 */
@Spi(order = 1)
public final class ClientExecutorFactory extends CommonExecutorFactory {

    @Override
    public Executor get(String name) {
        switch (name) {
            case PUSH_CLIENT: {
                ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(push_client
                        , new NamedPoolThreadFactory(T_PUSH_CLIENT_TIMER), (r, e) -> r.run() // run caller thread
                );
                executor.setRemoveOnCancelPolicy(true);
                return executor;
            }
            case ACK_TIMER: {
                ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(ack_timer,
                        new NamedPoolThreadFactory(T_ARK_REQ_TIMER),
                        (r, e) -> Logs.PUSH.error("one ack context was rejected, context=" + r)
                );
                executor.setRemoveOnCancelPolicy(true);
                return executor;
            }
            default:
                return super.get(name);
        }
    }
}
