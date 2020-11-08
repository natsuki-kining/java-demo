
package com.natsuki_kining.gupao.v2.distributed.netty.lion.cacher.redis;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.config.data.RedisNode;
import redis.clients.jedis.HostAndPort;

/**
 * redis 相关的配置信息
 */
public class RedisServer extends RedisNode {

    public RedisServer(String ip, int port) {
        super(ip, port);
    }

    public HostAndPort convert() {
        return new HostAndPort(host, port);
    }

}
