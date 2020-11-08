
package com.natsuki_kining.gupao.v2.distributed.netty.lion.core.push;

import java.util.concurrent.ScheduledExecutorService;


public interface PushTask extends Runnable {
    ScheduledExecutorService getExecutor();
}
