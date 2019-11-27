
package com.natsuki_kining.gupao.v2.distributed.rmi.alike;

/**
 * TODO
 *
 * @Author natsuki_kining
 * @Date 2019/11/19 15:28
 **/
public class Test {

    /**
     * 同一个工程内、直接访问没有问题。
     * @param args
     */
    public static void main(String[] args) {
        IHelloService helloService = new HelloServiceImpl();
        System.out.println(helloService.sayHello("natsuki_kining"));
    }

}
