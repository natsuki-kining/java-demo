package com.natsuki_kining.gupao.v2.source_code.design_pattern.proxy.gpproxy;

import com.natsuki_kining.gupao.v2.source_code.design_pattern.proxy.Person;
import com.natsuki_kining.gupao.v2.source_code.design_pattern.proxy.Work;

public class GpProxyTest {

    public static void main(String[] args) {

        Person person = (Person)new GpWorkProxy().getInstance(new Work());
        person.findWork();
    }
}
