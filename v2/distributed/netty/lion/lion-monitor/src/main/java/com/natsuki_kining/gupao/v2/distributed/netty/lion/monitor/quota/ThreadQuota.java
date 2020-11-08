
package com.natsuki_kining.gupao.v2.distributed.netty.lion.monitor.quota;


public interface ThreadQuota extends MonitorQuota {

    int daemonThreadCount();

    int threadCount();

    long totalStartedThreadCount();

    int deadLockedThreadCount();

}
