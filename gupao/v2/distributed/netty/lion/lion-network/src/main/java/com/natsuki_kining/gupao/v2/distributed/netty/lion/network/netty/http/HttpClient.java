
package com.natsuki_kining.gupao.v2.distributed.netty.lion.network.netty.http;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.service.Service;


public interface HttpClient extends Service {
    void request(RequestContext context) throws Exception;
}
