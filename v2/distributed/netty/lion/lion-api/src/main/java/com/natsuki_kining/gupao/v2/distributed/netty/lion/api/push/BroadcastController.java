
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.push;

import java.util.List;


public interface BroadcastController {

    String taskId();

    int qps();

    void updateQps(int qps);

    boolean isDone();

    int sendCount();

    void cancel();

    boolean isCancelled();

    int incSendCount(int count);

    void success(String... userIds);

    List<String> successUserIds();

}
