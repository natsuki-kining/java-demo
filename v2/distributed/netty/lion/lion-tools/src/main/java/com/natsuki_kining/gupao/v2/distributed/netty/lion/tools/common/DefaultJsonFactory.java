package com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.common;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.Spi;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.Json;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi.common.JsonFactory;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.Jsons;

@Spi
public final class DefaultJsonFactory implements JsonFactory, Json {
    @Override
    public <T> T fromJson(String json, Class<T> clazz) {
        return Jsons.fromJson(json, clazz);
    }

    @Override
    public String toJson(Object json) {
        return Jsons.toJson(json);
    }

    @Override
    public Json get() {
        return this;
    }
}