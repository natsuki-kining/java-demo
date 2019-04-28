
package com.natsuki_kining.gupao.v2.distributed.netty.lion.core.ack;


public interface AckCallback {
    void onSuccess(AckTask context);

    void onTimeout(AckTask context);
}
