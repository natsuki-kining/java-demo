
package com.natsuki_kining.gupao.v2.distributed.rmi.alike;

/**
 * TODO
 *
 * @Author natsuki_kining
 * @Date 2019/11/19 15:27
 **/
public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String msg) {
        return "Hello ,"+ msg;
    }
}
