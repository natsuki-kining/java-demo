package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.push;


public class PushException extends RuntimeException {

    public PushException(Throwable cause) {
        super(cause);
    }

    public PushException(String message) {
        super(message);
    }

    public PushException(String message, Throwable cause) {
        super(message, cause);
    }
}
