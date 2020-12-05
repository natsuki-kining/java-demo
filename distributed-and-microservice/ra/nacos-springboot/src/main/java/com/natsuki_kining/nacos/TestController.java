package com.natsuki_kining.nacos;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @NacosInjected
    private NamingService namingService;  //服务注册功能的Service

    @GetMapping("/get")
    public String get() throws NacosException {

        return namingService.
                getAllInstances("SpringBoot-Nacos").toString();
    }

    @PostMapping("/registry")
    public String registry() throws NacosException {
        Instance instance=new Instance();
        instance.setClusterName("TestCluster");
        instance.setEnabled(true);
        instance.setEphemeral(true);  //临时节点/持久化节点, CP（Raft）, AP（Distro）
        instance.setIp("192.168.50.206");
        instance.setPort(8888);
        instance.setWeight(10);  //1~100
        namingService.registerInstance("SpringBoot-Nacos",instance);
        return "SUCCESS";
    }

}
