

package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.connection;

import io.netty.channel.Channel;


public interface ConnectionManager {

    Connection get(Channel channel);

    Connection removeAndClose(Channel channel);

    void add(Connection connection);

    int getConnNum();

    void init();

    void destroy();
}
