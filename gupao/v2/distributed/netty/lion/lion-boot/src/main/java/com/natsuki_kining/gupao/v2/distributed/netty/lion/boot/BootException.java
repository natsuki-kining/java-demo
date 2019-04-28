
package com.natsuki_kining.gupao.v2.distributed.netty.lion.boot;

/**
 */
public class BootException extends RuntimeException {
    public BootException(String s) {
        super(s);
    }

    public BootException(String message, Throwable cause) {
        super(message, cause);
    }
}
