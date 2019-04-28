package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.service;

public interface Listener {
    void onSuccess(Object... args);

    void onFailure(Throwable cause);
}