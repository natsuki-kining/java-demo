
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common;


public interface Json {
    Json JSON = JsonFactory.create();

    <T> T fromJson(String json, Class<T> clazz);

    String toJson(Object json);
}
