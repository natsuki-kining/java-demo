
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.handler;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Plugin;


public interface BindValidator extends Plugin {
    boolean validate(String userId, String data);
}
