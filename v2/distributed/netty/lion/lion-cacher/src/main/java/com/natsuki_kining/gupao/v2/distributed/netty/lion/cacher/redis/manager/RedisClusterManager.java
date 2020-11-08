
package com.natsuki_kining.gupao.v2.distributed.netty.lion.cacher.redis.manager;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.cacher.redis.RedisServer;

import java.util.List;

public interface RedisClusterManager {

    void init();

    List<RedisServer> getServers();
}
