
package com.natsuki_kining.gupao.v2.distributed.rpc.server;

import com.natsuki_kining.gupao.v2.distributed.rpc.IRpc;

/**
 * 接口实现类
 *
 * @Author natsuki_kining
 * @Date 2019/11/26 16:10
 **/
public class RpcImpl implements IRpc {
    @Override
    public String hello(String name) {
        return "Hello "+name;
    }
}
