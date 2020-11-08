
package com.natsuki_kining.gupao.v2.distributed.netty.lion.monitor.quota;

public interface InfoQuota extends MonitorQuota {

    String pid();

    double load();
}
