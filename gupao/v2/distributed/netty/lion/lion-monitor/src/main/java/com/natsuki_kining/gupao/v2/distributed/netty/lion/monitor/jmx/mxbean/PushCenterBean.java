
package com.natsuki_kining.gupao.v2.distributed.netty.lion.monitor.jmx.mxbean;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.monitor.jmx.MBeanInfo;

import java.util.concurrent.atomic.AtomicLong;

/**
 *
 */
public final class PushCenterBean implements PushCenterMXBean, MBeanInfo {
    private final AtomicLong taskNum;

    public PushCenterBean(AtomicLong taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String getName() {
        return "PushCenter";
    }

    @Override
    public long getTaskNum() {
        return taskNum.get();
    }
}
