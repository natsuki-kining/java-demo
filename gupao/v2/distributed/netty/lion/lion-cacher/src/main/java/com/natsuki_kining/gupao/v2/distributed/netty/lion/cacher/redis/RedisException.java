package com.natsuki_kining.gupao.v2.distributed.netty.lion.cacher.redis;

/**
 *
 *
 */
public class RedisException extends RuntimeException {

    public RedisException() {
    }

    public RedisException(Throwable cause) {
        super(cause);
    }

    public RedisException(String message) {
        super(message);
    }

    public RedisException(String message, Throwable cause) {
        super(message, cause);
    }
}
